import 'dart:convert';
import 'dart:io';
import 'package:flutter_button/detail/detail_page.dart';
import 'package:flutter_easyrefresh/easy_refresh.dart';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:flutter_button/config/service_url.dart';
import 'package:flutter_button/utils/loading_progress.dart';
import 'package:flutter_button/utils/screen_util.dart';
import '../service/service_method.dart';

class HotUI extends StatefulWidget {
  @override
  _HotUIState createState() => _HotUIState();
}

class _HotUIState extends State<HotUI> {
  List datas = [];
  int page = 1;

  bool init = false;
  GlobalKey<RefreshHeaderState> _headerKeyList =
      new GlobalKey<RefreshHeaderState>();
  GlobalKey<RefreshFooterState> _footerKeyList =
      new GlobalKey<RefreshFooterState>();
  GlobalKey<RefreshHeaderState> _headerKeyGrid =
      new GlobalKey<RefreshHeaderState>();
  GlobalKey<RefreshFooterState> _footerKeyGrid =
      new GlobalKey<RefreshFooterState>();

  @override
  void initState() {
    super.initState();
    homePageBelowConten();
  }

  void homePageBelowConten() async {
    var response =
        await Dio().post(servicePath['homePageBelowConten'], data: page);
    if (response.statusCode == 200) {
      var result = response.data;
      var data = json.decode(result.toString());
      setState(() {
        datas.addAll(data['data']);
        init = true;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    ScreenUtil.instance = ScreenUtil(width: 750, height: 1334)..init(context);
    return Scaffold(
      appBar: AppBar(
        title: Text('火爆商品'),
      ),
      body: init
          ? EasyRefresh(
              onRefresh: () async {
                setState(() {
                  page = 1;
                });

                homePageBelowConten();
              },
              loadMore: () async {
                setState(() {
                  page = page + 1;
                });

                homePageBelowConten();
              },
              refreshHeader: ClassicsHeader(
                key: _headerKeyGrid,
                bgColor: Colors.transparent,
                textColor: Colors.black,
              ),
              refreshFooter: ClassicsFooter(
                key: _footerKeyGrid,
                loadHeight: 50.0,
                textColor: Colors.black,
                bgColor: Colors.transparent,
                showMore: true,
                noMoreText: '',
                moreInfo: '加载中。。。',
                loadReadyText: '上拉加载',
              ),
              child: GridView.builder(
                gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                    crossAxisCount: 2),
                itemBuilder: (BuildContext context, int index) {
                  Map m = datas[index];
                  return SingleChildScrollView(
                    physics: NeverScrollableScrollPhysics(),
                    child: InkWell(
                      child: Container(
                        child: Column(
                          children: <Widget>[
                            Container(
                                width: ScreenUtil.screenWidth / 2,
                                height: ScreenUtil().setHeight(300),
                                child: Image.network(
                                  '${m['image']}',
                                  fit: BoxFit.fill,
                                )),
                            Container(
                              child: Center(
                                child: Text(
                                  m['name'],
                                  style: TextStyle(color: Colors.pink),
                                ),
                              ),
                            ),
                            Container(
                              child: Row(
                                mainAxisAlignment: MainAxisAlignment.start,
                                children: <Widget>[
                                  Container(
                                    margin: EdgeInsets.only(left: 10.0),
                                    child: Text(
                                      '￥${m['mallPrice']}',
                                      style: TextStyle(color: Colors.black),
                                    ),
                                  ),
                                  Container(
                                    margin: EdgeInsets.only(left: 30.0),
                                    child: Text(
                                      '￥${m['price']}',
                                      style: TextStyle(
                                          decoration:
                                              TextDecoration.lineThrough,
                                          color: Colors.grey),
                                    ),
                                  ),
                                ],
                              ),
                            ),
                          ],
                        ),
                      ),
                      onTap: (){

                        Map newM = mapToMap2(m);

                        Navigator.of(context)
                            .push(MaterialPageRoute(builder: (BuildContext ctx) {
                          return DetailPage(map: newM,);
                        }));
                      },
                    ),
                  );
                },
                itemCount: datas.length,
              ))
          : Center(
              child: Loading(),
            ),
    );
  }

  Map mapToMap2(Map m) {
      Map newM = Map();
    newM.putIfAbsent("goodsName", () => m['name']);
    newM.putIfAbsent("image", () => m['image']);
    newM.putIfAbsent("presentPrice", () => m['mallPrice']);
    newM.putIfAbsent("oriPrice", () => m['price']);
    newM.putIfAbsent("goodsId", () => '火爆商品');
    return newM;
  }
}
