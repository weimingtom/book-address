参照我的博客：http://blog.csdn.net/jamesliulyc/article/details/6703487
为了简单起见，这里就不用这个截图做例子了，下面就用写一个最简单的Demo。

第一步：还是先建立底部的选项卡(其实就是一个TableLayout布局),代码如下(main.xml)：

view plaincopy to clipboardprint?<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
> xmlns:android="http://schemas.android.com/apk/res/android"
> > android:layout\_width="wrap\_content"
> > android:layout\_height="wrap\_content"
> > android:background="#ffffff">
<TableLayout android:layout\_width="fill\_parent"
> > > android:layout\_height="54dip"
> > > android:orientation="horizontal"
> > > android:layout\_gravity="bottom"
> > > android:layout\_alignParentBottom="true"
> > > xmlns:android="http://schemas.android.com/apk/res/android"
> > > >
> > > <TableRow
> > > > android:layout\_width="fill\_parent"
> > > > android:layout\_height="54dip"
> > > > >
> > > > <Button
> > > > > android:id="@+id/btn1"
> > > > > android:background="#888888"
> > > > > android:layout\_width="70dip"
> > > > > android:layout\_height="54dip"
> > > > > android:layout\_weight="1"
> > > > > android:text="Button 1"
> > > > > />

> > > > <Button
> > > > > android:id="@+id/btn2"
> > > > > android:background="#888888"
> > > > > android:layout\_width="70dip"
> > > > > android:layout\_height="54dip"
> > > > > android:layout\_weight="1"
> > > > > android:text="Button 2"
> > > > > />

> > > > <Button
> > > > > android:background="#888888"
> > > > > android:id="@+id/btn3"
> > > > > android:layout\_width="70dip"
> > > > > android:layout\_height="54dip"
> > > > > android:layout\_weight="1"
> > > > > android:text="Button 3"
> > > > > />

> > > > <Button
> > > > > android:background="#888888"
> > > > > android:id="@+id/btn4"
> > > > > android:layout\_width="70dip"
> > > > > android:layout\_height="54dip"
> > > > > android:layout\_weight="1"
> > > > > android:text="Button 4"
> > > > > />

> > > 

Unknown end tag for &lt;/TableRow&gt;



> 

Unknown end tag for &lt;/TableLayout&gt;




Unknown end tag for &lt;/RelativeLayout&gt;



第二步：就是建立4个xml布局文件，里面可以只写一个TextView，命名为btn1\_layout.xml,btn2\_layout.xml,btn3\_layout.xml,btn4\_layout.xml.类似如下：



view plaincopy to clipboardprint?<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
> xmlns:android="http://schemas.android.com/apk/res/android"
> > android:layout\_width="fill\_parent"
> > android:layout\_height="fill\_parent">
> > <TextView
> > > android:layout\_width="fill\_parent"
> > > android:layout\_height="fill\_parent"
> > > android:text="Button 1"
> > > android:textSize="36sp"
> > > android:textColor="#4a9ad8"

> > />


Unknown end tag for &lt;/LinearLayout&gt;


<?xml version="1.0" encoding="utf-8"?>
<LinearLayout

> xmlns:android="http://schemas.android.com/apk/res/android"
> > android:layout\_width="fill\_parent"
> > android:layout\_height="fill\_parent">
> > <TextView
> > > android:layout\_width="fill\_parent"
> > > > android:layout\_height="fill\_parent"
> > > > android:text="Button 1"
> > > > android:textSize="36sp"
> > > > android:textColor="#4a9ad8"

> > />


Unknown end tag for &lt;/LinearLayout&gt;






第三步：


> 将下列代码插入到第一步中main.xml中，位于TableLayout之上

view plaincopy to clipboardprint?<ViewStub
> android:id="@+id/btn1ViewStub"
> android:layout="@layout/btn1\_layout"
> android:layout\_width="fill\_parent"
> android:layout\_height="fill\_parent"
> />
> <ViewStub
> > android:id="@+id/btn2ViewStub"
> > android:layout="@layout/btn2\_layout"
> > android:layout\_width="fill\_parent"
> > android:layout\_height="fill\_parent"
> > />

> <ViewStub
> > android:id="@+id/btn3ViewStub"
> > android:layout="@layout/btn3\_layout"
> > android:layout\_width="fill\_parent"
> > android:layout\_height="fill\_parent"
> > />

> <ViewStub
> > android:id="@+id/btn4ViewStub"
> > android:layout="@layout/btn4\_layout"
> > android:layout\_width="fill\_parent"
> > android:layout\_height="fill\_parent"
> > />
<ViewStub

> android:id="@+id/btn1ViewStub"
> android:layout="@layout/btn1\_layout"
> android:layout\_width="fill\_parent"
> android:layout\_height="fill\_parent"
> />
> <ViewStub
> > android:id="@+id/btn2ViewStub"
> > android:layout="@layout/btn2\_layout"
> > android:layout\_width="fill\_parent"
> > android:layout\_height="fill\_parent"
> > />

> <ViewStub
> > android:id="@+id/btn3ViewStub"
> > android:layout="@layout/btn3\_layout"
> > android:layout\_width="fill\_parent"
> > android:layout\_height="fill\_parent"
> > />

> <ViewStub
> > android:id="@+id/btn4ViewStub"
> > android:layout="@layout/btn4\_layout"
> > android:layout\_width="fill\_parent"
> > android:layout\_height="fill\_parent"
> > />




> 第四步：Activity中，产生点击事件后，首先要将所有的ViewStub设置成不可见，否则将会出问题（你可以试试），java代码如下，具体就不解释了，能用ViewStub相信能看懂。

view plaincopy to clipboardprint?package com.tab.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

public class MainActivity extends Activity {

> private ViewStub[.md](.md) viewStub = new ViewStub[4](4.md);
> private Button currentBtn;
> private Button lastBtn;

> private int[.md](.md) tabBtnIds = {R.id.btn1, R.id.btn2,
> > R.id.btn3, R.id.btn4};


> private Button[.md](.md) tabBtn = new Button[4](4.md);

> @Override
> public void onCreate(Bundle savedInstanceState) {
> > super.onCreate(savedInstanceState);
> > setContentView(R.layout.main);
> > viewStub[0](0.md) = (ViewStub) findViewById(R.id.btn1ViewStub);
> > viewStub[1](1.md) = (ViewStub) findViewById(R.id.btn2ViewStub);
> > viewStub[2](2.md) =(ViewStub) findViewById(R.id.btn3ViewStub);
> > viewStub[3](3.md) = (ViewStub) findViewById(R.id.btn4ViewStub);
> > currentBtn = (Button) findViewById(R.id.btn2);
> > TabBtnClickListener tabBtnListener = new TabBtnClickListener();
> > for(int i=0; i<tabBtnIds.length; i++) {
> > > tabBtn[i](i.md) = (Button) findViewById(tabBtnIds[i](i.md));
> > > tabBtn[i](i.md).setOnClickListener(tabBtnListener);

> > }


> }

> class TabBtnClickListener implements View.OnClickListener {

> @Override
> public void onClick(View v) {
> > lastBtn = currentBtn;
> > currentBtn = (Button) v;
> > if(currentBtn.getId() == lastBtn.getId()) {
> > > return;

> > }
> > currentBtn.setBackgroundColor(Color.BLUE);
> > lastBtn.setBackgroundColor(Color.GRAY);
> > int currentIndex = -1;
> > switch(currentBtn.getId()) {
> > > case R.id.btn1:
> > > > currentIndex = 0;
> > > > break;

> > > case R.id.btn2:
> > > > currentIndex = 1;
> > > > break;

> > > case R.id.btn3:
> > > > currentIndex = 2;
> > > > break;

> > > case R.id.btn4:
> > > > currentIndex = 3;
> > > > break;

> > }
> > for(int i=0; i<viewStub.length; i++) {
> > > viewStub[i](i.md).setVisibility(View.INVISIBLE);

> > }
> > for(int i=0; i<viewStub.length; i++) {
> > > if(currentIndex == -1) {
> > > > break;

> > > }
> > > if(currentIndex != i) {
> > > > viewStub[i](i.md).setVisibility(View.INVISIBLE);

> > > } else {
> > > > viewStub[i](i.md).setVisibility(View.VISIBLE);

> > > }

> > }

> }
> }
}