import 'package:flutter/material.dart';
import 'package:flutter_button/cart_test.dart';
import 'package:flutter_button/constants/color.dart';
import 'package:flutter_button/page/cart_page.dart';
import 'package:flutter_button/page/index_main.dart';
import 'package:flutter_button/pay/pay_page.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter/services.dart';
import '../service/service_method.dart';

class DetailPage extends StatefulWidget {
  final Map map;

  DetailPage({Key key, this.map}) : super(key: key);

  @override
  _DetailPageState createState() => _DetailPageState();
}

//goodsId
//presentPrice
//oriPrice

class _DetailPageState extends State<DetailPage> {
  String CHANNEL = "cart/query";
  String CHANNEL1 = "cart/add";
  String CHANNEL2 = "cart/update";

  int count = 1;

  static const query = const MethodChannel("cart/query");
  static const add = const MethodChannel("cart/add");
  static const add_count = const MethodChannel("cart/count");
  static const update = const MethodChannel("cart/update");

  @override
  Widget build(BuildContext context) {
    ScreenUtil.instance = ScreenUtil(width: 750, height: 1334)..init(context);
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.map['goodsName']),
        elevation: 0.0,
      ),
      body: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Container(
              child: Center(
                child: Image.network(widget.map['image']),
              ),
            ),
            Container(
              margin: EdgeInsets.all(5.0),
              child: Text(
                widget.map['goodsName'],
                style: TextStyle(fontSize: ScreenUtil().setSp(40)),
                textAlign: TextAlign.left,
              ),
            ),
            Container(
              margin: EdgeInsets.all(5.0),
              child: Text(
                '编号: ' + widget.map['goodsId'],
                style: TextStyle(
                  color: Colors.black45,
                ),
              ),
            ),
            Container(
              margin: EdgeInsets.only(left: 5.0),
              child: Row(
                children: <Widget>[
                  Container(
                    child: Text(
                      '￥ ' + widget.map['presentPrice'].toString(),
                      style: TextStyle(fontSize: 20, color: Colors.red),
                    ),
                  ),
                  Container(
                    margin: EdgeInsets.all(20.0),
                    child: Text('市场价:'),
                  ),
                  Container(
                    child: Text(
                      '￥' + widget.map['oriPrice'].toString(),
                      style: TextStyle(
                          decoration: TextDecoration.lineThrough,
                          color: Colors.grey),
                    ),
                  ),
                ],
              ),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                InkWell(
                  onTap: () {
                    showBottomWidget(
                      context,
                      SingleChildScrollView(
                        child: Container(
                          margin: EdgeInsets.all(20.0),
                          height: ScreenUtil().setHeight(150),
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: <Widget>[
                              Container(
                                margin: EdgeInsets.all(1.0),
                                child: Text(
                                  '购买数量',
                                ),
                              ),
                              Container(
                                child: Divider(),
                              ),
                              Container(
                                child: Row(
                                  // mainAxisAlignment: MainAxisAlignment.center,
                                  children: <Widget>[
                                    GestureDetector(
                                      onTap: () => {_dix_count()},
                                      child: Container(
                                        width: ScreenUtil().setWidth(50),
                                        height: ScreenUtil().setHeight(50),
                                        decoration: BoxDecoration(
                                            border: _getRemoveBtBorder()),
                                        child: Icon(Icons.remove,
                                            color: _getRemovebuttonColor()),
                                      ),
                                    ),
                                    Container(
                                        alignment: Alignment.center,
                                        width: ScreenUtil().setWidth(50),
                                        height: ScreenUtil().setHeight(50),
                                        decoration: BoxDecoration(
                                            border: Border.all(
                                                color: KColorConstant
                                                    .cartItemCountTxtColor,
                                                width: 1)),
                                        child: Text(
                                          '${count}',
                                          style: TextStyle(
                                              fontSize: 13,
                                              fontWeight: FontWeight.bold,
                                              color: KColorConstant
                                                  .cartItemCountTxtColor),
                                        )),
                                    GestureDetector(
                                      onTap: () => {_add_count()},
                                      child: Container(
                                        alignment: Alignment.center,
                                        width: ScreenUtil().setWidth(50),
                                        height: ScreenUtil().setHeight(50),
                                        decoration: BoxDecoration(
                                            border: _getAddBtBorder()),
                                        child: Icon(Icons.add,
                                            color: _getAddbuttonColor()),
                                      ),
                                    ),
                                    Container(
                                      margin: EdgeInsets.only(left: 10.0),
                                      width: ScreenUtil().setWidth(210),
                                      height: ScreenUtil().setHeight(60),
                                      alignment: Alignment.center,
                                      color: Colors.green,
                                      child: InkWell(
                                        onTap: () {
                                          Map m=widget.map;
                                          //添加新元素 给不存在的key ,赋值
                                          m.putIfAbsent("count", ()=> count );
                                          print(m);
                                          add.invokeMethod("add", m);
                                          Navigator.of(context).push(
                                            MaterialPageRoute(
                                              builder: (BuildContext ctx) {
                                                return CartTest();
                                              },
                                            ),
                                          );
                                        },
                                        child: Text(
                                          '确认加入购物车',
                                          style: TextStyle(color: Colors.white),
                                        ),
                                      ),
                                    ),
                                  ],
                                ),
                              ),
                            ],
                          ),
                        ),
                      ),
                    );
                  },
                  child: Container(
                    width: ScreenUtil().setHeight(180),
                    height: ScreenUtil().setHeight(60),
                    color: Colors.green,
                    child: Center(
                      child: Text(
                        '加入购物车',
                        style: TextStyle(color: Colors.white),
                      ),
                    ),
                  ),
                ),
                InkWell(
                  onTap: () {
                    Navigator.of(context)
                        .push(MaterialPageRoute(builder: (BuildContext ctx) {
                      return PayPage(
                        map: widget.map,
                      );
                    }));
                  },
                  child: Container(
                    width: ScreenUtil().setHeight(180),
                    height: ScreenUtil().setHeight(60),
                    color: Colors.red,
                    child: Center(
                      child: Text(
                        '立即购买',
                        style: TextStyle(color: Colors.white),
                      ),
                    ),
                  ),
                )
              ],
            ),
          ],
        ),
      ),
    );
  }

  void _add_count() {
    setState(() {
     ++ count;
    });
  }

  void _dix_count() {
    setState(() {
     -- count;
    });
  }

  Color _getRemovebuttonColor() {
    return KColorConstant.cartItemCountTxtColor;
  }

  Border _getRemoveBtBorder() {
    return Border(
        bottom: BorderSide(width: 1, color: _getRemovebuttonColor()),
        top: BorderSide(width: 1, color: _getRemovebuttonColor()),
        left: BorderSide(width: 1, color: _getRemovebuttonColor()));
  }

  Border _getAddBtBorder() {
    return Border(
        bottom: BorderSide(width: 1, color: _getAddbuttonColor()),
        top: BorderSide(width: 1, color: _getAddbuttonColor()),
        right: BorderSide(width: 1, color: _getAddbuttonColor()));
  }

  _getAddbuttonColor() {
    return KColorConstant.cartItemCountTxtColor;
  }
}