package com.luck.pictureselector;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.luck.pictureselector.adapter.GridImageAdapter;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {
    private final static String TAG = MainActivity.class.getSimpleName();
    private List<LocalMedia> selectList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GridImageAdapter adapter;
    private int maxSelectNum = 9;
    public String role_name;
    public int role_id;
    private TextView tv_select_num;
    private ImageView left_back, minus, plus;
    private RadioGroup rgb_crop, rgb_style, rgb_photo_mode;
    private int aspect_ratio_x, aspect_ratio_y;
    private CheckBox cb_voice, cb_choose_mode, cb_isCamera, cb_isGif,
            cb_preview_img, cb_preview_video, cb_crop, cb_compress,
            cb_mode, cb_hide, cb_crop_circular, cb_styleCrop, cb_showCropGrid,
            cb_showCropFrame, cb_preview_audio;
    private int themeId;
    private int chooseMode = PictureMimeType.ofAll();
    final OkHttpClient client = new OkHttpClient();

    private GridView rolesList;

    private TextView r;

    private EditText etMeans;
    private EditText etRoles;

    private Button submitRole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMeans = (EditText) findViewById(R.id.etMeans);
        etRoles = (EditText) findViewById(R.id.etRoles);
        submitRole = (Button) findViewById(R.id.submitRole);

        r = (TextView) findViewById(R.id.role_name);
        rolesList = (GridView) findViewById(R.id.roles_list);
        themeId = R.style.picture_default_style;
        minus = (ImageView) findViewById(R.id.minus);
        plus = (ImageView) findViewById(R.id.plus);
        tv_select_num = (TextView) findViewById(R.id.tv_select_num);
        rgb_crop = (RadioGroup) findViewById(R.id.rgb_crop);
        rgb_style = (RadioGroup) findViewById(R.id.rgb_style);
        rgb_photo_mode = (RadioGroup) findViewById(R.id.rgb_photo_mode);
        cb_voice = (CheckBox) findViewById(R.id.cb_voice);
        cb_choose_mode = (CheckBox) findViewById(R.id.cb_choose_mode);
        cb_isCamera = (CheckBox) findViewById(R.id.cb_isCamera);
        cb_isGif = (CheckBox) findViewById(R.id.cb_isGif);
        cb_preview_img = (CheckBox) findViewById(R.id.cb_preview_img);
        cb_preview_video = (CheckBox) findViewById(R.id.cb_preview_video);
        cb_crop = (CheckBox) findViewById(R.id.cb_crop);
        cb_styleCrop = (CheckBox) findViewById(R.id.cb_styleCrop);
        cb_compress = (CheckBox) findViewById(R.id.cb_compress);
        cb_mode = (CheckBox) findViewById(R.id.cb_mode);
        cb_showCropGrid = (CheckBox) findViewById(R.id.cb_showCropGrid);
        cb_showCropFrame = (CheckBox) findViewById(R.id.cb_showCropFrame);
        cb_preview_audio = (CheckBox) findViewById(R.id.cb_preview_audio);
        cb_hide = (CheckBox) findViewById(R.id.cb_hide);
        cb_crop_circular = (CheckBox) findViewById(R.id.cb_crop_circular);
        rgb_crop.setOnCheckedChangeListener(this);
        rgb_style.setOnCheckedChangeListener(this);
        rgb_photo_mode.setOnCheckedChangeListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        left_back = (ImageView) findViewById(R.id.left_back);
        left_back.setOnClickListener(this);
        minus.setOnClickListener(this);
        plus.setOnClickListener(this);
        cb_crop.setOnCheckedChangeListener(this);
        cb_crop_circular.setOnCheckedChangeListener(this);
        cb_compress.setOnCheckedChangeListener(this);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(MainActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(MainActivity.this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(MainActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(MainActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(MainActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });

        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(MainActivity.this);
                } else {
                    Toast.makeText(MainActivity.this,
                            getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });


        QUERYR0LE();


    }

    private void QUERYR0LE() {
        Request request = new Request.Builder()
                .get()
                .url("http://47.96.78.67:7070/video/query/roles")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                final RoleBeans roleBeans = new Gson().fromJson(json, RoleBeans.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final List<RoleBeans.DataBean> data = roleBeans.getData();
                        rolesList.setAdapter(ada = new RolesAdapter(data));

                        rolesOperation();

                        etOperation();

                        submitRole.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final String s = etRoles.getText().toString();
                                boolean flag = false;

                                addROLE(s);

                            }
                        });
                    }
                });
            }
        });
    }

    private void etOperation() {

    }

    void addROLE(String name) {
        FutureTask<String> futureTask = new FutureTask<String>(new Get(name));
        new Thread(futureTask).start();
        try {
            final String s = futureTask.get();
//            System.out.println(s);
            if (futureTask.isDone()) {
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                QUERYR0LE();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    RolesAdapter ada;

    class Get implements Callable<String> {
        String name;

        public Get(String name) {
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            return thread(name);
        }
    }

    private String thread(String name) throws Exception {
        FormBody.Builder fb = new FormBody.Builder();
        fb.add("roleName", name);
        Request request = new Request.Builder()
                .post(fb.build())
                .url(UriConfig.URL_ADD_ROLE)
                .build();
        final Response execute = client.newCall(request).execute();
        return execute.body().string();
    }


    class RolesAdapter extends BaseAdapter {

        Click click;

        public void setClick(Click click) {
            this.click = click;
        }

        List<RoleBeans.DataBean> data;

        public RolesAdapter(List<RoleBeans.DataBean> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        ViewHolder vh;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            if (convertView == null) {
                vh = new ViewHolder();
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.roles_item, null);
                vh.name = (TextView) convertView.findViewById(R.id.role_name);

                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            final RoleBeans.DataBean d = data.get(position);
            vh.name.setText(d.roleName);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.onItem(d);
                    //vh.name.setTextColor(Color.parseColor("#ff0000"));

                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView name;
        }

    }

    class RoleBeans {

        /**
         * code : 0
         * msg : null
         * data : [{"id":1,"roleName":"茹茹姐","createAt":"2018-12-12T16:00:00.000+0000"},{"id":2,"roleName":"小歪","createAt":"2018-12-12T16:00:00.000+0000"},{"id":3,"roleName":"胖虎","createAt":"2018-12-12T16:00:00.000+0000"},{"id":4,"roleName":"娜姐","createAt":"2018-12-12T16:00:00.000+0000"},{"id":5,"roleName":"萌芽熊","createAt":"2018-12-12T16:00:00.000+0000"}]
         */

        private int code;
        private Object msg;
        /**
         * id : 1
         * roleName : 茹茹姐
         * createAt : 2018-12-12T16:00:00.000+0000
         */

        private List<DataBean> data;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public Object getMsg() {
            return msg;
        }

        public void setMsg(Object msg) {
            this.msg = msg;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public class DataBean {
            private int id;
            private String roleName;
            private String createAt;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRoleName() {
                return roleName;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }

            public String getCreateAt() {
                return createAt;
            }

            public void setCreateAt(String createAt) {
                this.createAt = createAt;
            }
        }
    }

    interface Click {
        void onItem(RoleBeans.DataBean bean);
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            boolean mode = cb_mode.isChecked();
            if (mode) {
                // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(MainActivity.this)
                        .openGallery(chooseMode)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                        .maxSelectNum(maxSelectNum)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(4)// 每行显示个数
                        .selectionMode(cb_choose_mode.isChecked() ?
                                PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选
                        .previewImage(cb_preview_img.isChecked())// 是否可预览图片
                        .previewVideo(cb_preview_video.isChecked())// 是否可预览视频
                        .enablePreviewAudio(cb_preview_audio.isChecked()) // 是否可播放音频
                        .isCamera(cb_isCamera.isChecked())// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                        //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                        .enableCrop(cb_crop.isChecked())// 是否裁剪
                        .compress(cb_compress.isChecked())// 是否压缩
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        //.compressSavePath(getPath())//压缩图片保存地址
                        //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(cb_hide.isChecked() ? false : true)// 是否显示uCrop工具栏，默认不显示
                        .isGif(cb_isGif.isChecked())// 是否显示gif图片
                        .freeStyleCropEnabled(cb_styleCrop.isChecked())// 裁剪框是否可拖拽
                        .circleDimmedLayer(cb_crop_circular.isChecked())// 是否圆形裁剪
                        .showCropFrame(cb_showCropFrame.isChecked())// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(cb_showCropGrid.isChecked())// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .openClickSound(cb_voice.isChecked())// 是否开启点击声音
                        .selectionMedia(selectList)// 是否传入已选图片
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                        //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.rotateEnabled() // 裁剪是否可旋转图片
                        //.scaleEnabled()// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.videoSecond()//显示多少秒以内的视频or音频也可适用
                        //.recordVideoSecond()//录制视频秒数 默认60s
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            } else {
                // 单独拍照
                PictureSelector.create(MainActivity.this)
                        .openCamera(chooseMode)// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                        .theme(themeId)// 主题样式设置 具体参考 values/styles
                        .maxSelectNum(maxSelectNum)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .selectionMode(cb_choose_mode.isChecked() ?
                                PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选
                        .previewImage(cb_preview_img.isChecked())// 是否可预览图片
                        .previewVideo(cb_preview_video.isChecked())// 是否可预览视频
                        .enablePreviewAudio(cb_preview_audio.isChecked()) // 是否可播放音频
                        .isCamera(cb_isCamera.isChecked())// 是否显示拍照按钮
                        .enableCrop(cb_crop.isChecked())// 是否裁剪
                        .compress(cb_compress.isChecked())// 是否压缩
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(cb_hide.isChecked() ? false : true)// 是否显示uCrop工具栏，默认不显示
                        .isGif(cb_isGif.isChecked())// 是否显示gif图片
                        .freeStyleCropEnabled(cb_styleCrop.isChecked())// 裁剪框是否可拖拽
                        .circleDimmedLayer(cb_crop_circular.isChecked())// 是否圆形裁剪
                        .showCropFrame(cb_showCropFrame.isChecked())// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(cb_showCropGrid.isChecked())// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .openClickSound(cb_voice.isChecked())// 是否开启点击声音
                        .selectionMedia(selectList)// 是否传入已选图片
                        .previewEggs(false)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.cropCompressQuality(90)// 裁剪压缩质量 默认为100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        //.rotateEnabled() // 裁剪是否可旋转图片
                        //.scaleEnabled()// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.videoSecond()////显示多少秒以内的视频or音频也可适用
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            }
        }

    };

    public static final String ip = "http://116.62.168.251:8080/";
    //    public static final String ip = "http://localhost:8080/";
    String name = "http://oss-toplu1015.oss-cn-hangzhou.aliyuncs.com/video/";
    String namePicture = "http://oss-toplu1015.oss-cn-hangzhou.aliyuncs.com/picture/";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    DialogUtils.show(this);
                    selectList = PictureSelector.obtainMultipleResult(data);
                    String path = selectList.get(0).getPath();
                    final UploadFileVideo uploadFile = new UploadFileVideo(path, this);
                    uploadFile.start();
                    uploadFile.setBack(new UploadFileVideo.VideoCallBack() {
                        @Override
                        public void onSuccess(String name, String name1, String name2) {
                            okhttp(name, name1, name2, uploadFile.MD5());
                        }

                        @Override
                        public void onFailure() {

                        }
                    });

                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();


                    break;
            }
        }
    }

    private void okhttp(String name, String name1, String name2, String md5) {
//        FormBody formBody = new FormBody.Builder()
//                .add("videoUri", name)
//                .add("picUri", name1)
//                .add("md5", name2)
////                .add("means","其他")
//                .build();
        //new
        final String s = String.valueOf(role_id);
        final String s1 = etMeans.getText().toString();
        if (TextUtils.isEmpty(s1)) {
            Toast.makeText(this, "请提交非空备注", Toast.LENGTH_SHORT).show();
            return;
        }
        FormBody formBody = new FormBody.Builder()
                .add("uri", name)
                .add("means", s1)
                .add("imguri", name1)
                .add("md5", md5)
                .add("role", role_name)
                .add("role_id", s)
                .build();

        OkHttpClient okHttpClient = genericClient();

        final Request request = new Request.Builder()

                .post(formBody).url(UriConfig.URL_ADD_NEW).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                e.printStackTrace();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        DialogUtils.dismiss();
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String s = response.body().string();

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        DialogUtils.dismiss();
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

                        finish();
                    }
                });
            }
        });
    }


    public static OkHttpClient genericClient() {

        OkHttpClient httpClient = new OkHttpClient.Builder()

                .addInterceptor(new Interceptor() {

                    @Override

                    public Response intercept(Chain chain) throws IOException {

                        Request request = chain.request()

                                .newBuilder()

                                .addHeader("Content-Type", "multipart/form-data; charset=UTF-8")

                                .addHeader("Accept-Encoding", "gzip, deflate")

                                .addHeader("Connection", "keep-alive")

                                .addHeader("Accept", "*/*")

                                .addHeader("Cookie", "add cookies here")

                                .build();

                        return chain.proceed(request);

                    }


                })

                .build();


        return httpClient;
    }


    Handler mHandler = new Handler();
    String URL_SAVE = "http://47.96.78.67/VideoOperactor/save.do";

    /**
     * 文件转base64字符串
     *
     * @param file
     * @return
     */
    public static String fileToBase64(File file) {
        String base64 = null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            int length = in.read(bytes);
            base64 = Base64.encodeToString(bytes, 0, length, Base64.DEFAULT);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return base64;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_back:
                finish();
                break;
            case R.id.minus:
                if (maxSelectNum > 1) {
                    maxSelectNum--;
                }
                tv_select_num.setText(maxSelectNum + "");
                adapter.setSelectMax(maxSelectNum);
                break;
            case R.id.plus:
                maxSelectNum++;
                tv_select_num.setText(maxSelectNum + "");
                adapter.setSelectMax(maxSelectNum);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_all:
                chooseMode = PictureMimeType.ofAll();
                cb_preview_img.setChecked(true);
                cb_preview_video.setChecked(true);
                cb_isGif.setChecked(false);
                cb_preview_video.setChecked(true);
                cb_preview_img.setChecked(true);
                cb_preview_video.setVisibility(View.VISIBLE);
                cb_preview_img.setVisibility(View.VISIBLE);
                cb_compress.setVisibility(View.VISIBLE);
                cb_crop.setVisibility(View.VISIBLE);
                cb_isGif.setVisibility(View.VISIBLE);
                cb_preview_audio.setVisibility(View.GONE);
                break;
            case R.id.rb_image:
                chooseMode = PictureMimeType.ofImage();
                cb_preview_img.setChecked(true);
                cb_preview_video.setChecked(false);
                cb_isGif.setChecked(false);
                cb_preview_video.setChecked(false);
                cb_preview_video.setVisibility(View.GONE);
                cb_preview_img.setChecked(true);
                cb_preview_audio.setVisibility(View.GONE);
                cb_preview_img.setVisibility(View.VISIBLE);
                cb_compress.setVisibility(View.VISIBLE);
                cb_crop.setVisibility(View.VISIBLE);
                cb_isGif.setVisibility(View.VISIBLE);
                break;
            case R.id.rb_video:
                chooseMode = PictureMimeType.ofVideo();
                cb_preview_img.setChecked(false);
                cb_preview_video.setChecked(true);
                cb_isGif.setChecked(false);
                cb_isGif.setVisibility(View.GONE);
                cb_preview_video.setChecked(true);
                cb_preview_video.setVisibility(View.VISIBLE);
                cb_preview_img.setVisibility(View.GONE);
                cb_preview_img.setChecked(false);
                cb_compress.setVisibility(View.GONE);
                cb_preview_audio.setVisibility(View.GONE);
                cb_crop.setVisibility(View.GONE);
                break;
            case R.id.rb_audio:
                chooseMode = PictureMimeType.ofAudio();
                cb_preview_audio.setVisibility(View.VISIBLE);
                break;
            case R.id.rb_crop_default:
                aspect_ratio_x = 0;
                aspect_ratio_y = 0;
                break;
            case R.id.rb_crop_1to1:
                aspect_ratio_x = 1;
                aspect_ratio_y = 1;
                break;
            case R.id.rb_crop_3to4:
                aspect_ratio_x = 3;
                aspect_ratio_y = 4;
                break;
            case R.id.rb_crop_3to2:
                aspect_ratio_x = 3;
                aspect_ratio_y = 2;
                break;
            case R.id.rb_crop_16to9:
                aspect_ratio_x = 16;
                aspect_ratio_y = 9;
                break;
            case R.id.rb_default_style:
                themeId = R.style.picture_default_style;
                break;
            case R.id.rb_white_style:
                themeId = R.style.picture_white_style;
                break;
            case R.id.rb_num_style:
                themeId = R.style.picture_QQ_style;
                break;
            case R.id.rb_sina_style:
                themeId = R.style.picture_Sina_style;
                break;
        }
    }

    private int x = 0, y = 0;

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_crop:
                rgb_crop.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                cb_hide.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                cb_crop_circular.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                cb_styleCrop.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                cb_showCropFrame.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                cb_showCropGrid.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                break;
            case R.id.cb_crop_circular:
                if (isChecked) {
                    x = aspect_ratio_x;
                    y = aspect_ratio_y;
                    aspect_ratio_x = 1;
                    aspect_ratio_y = 1;
                } else {
                    aspect_ratio_x = x;
                    aspect_ratio_y = y;
                }
                rgb_crop.setVisibility(isChecked ? View.GONE : View.VISIBLE);
                if (isChecked) {
                    cb_showCropFrame.setChecked(false);
                    cb_showCropGrid.setChecked(false);
                } else {
                    cb_showCropFrame.setChecked(true);
                    cb_showCropGrid.setChecked(true);
                }
                break;
        }
    }

    /**
     * 自定义压缩存储地址
     *
     * @return
     */
    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/Luban/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    private void rolesOperation() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ada.setClick(new Click() {
                    @Override
                    public void onItem(RoleBeans.DataBean bean) {
                        role_id = bean.getId();
                        role_name = bean.getRoleName();
                        r.setText("选中role : " + role_name);

                    }
                });
            }
        });

    }
}
