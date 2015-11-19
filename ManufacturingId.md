If you have been developing in Android for any length of time, you’ll most likely be aware that one of the most useful portions the resource framework is the fact that Views and other resources can be given an android:id tag in their XML declaration, and Android will make sure to compile all the ids into unique integer values that can be accessed from Java code by calling R.id.. This allows us to reduce the number of event listeners an application requires because it can uniquely identify the view that triggered the event by matching its id.  And, of course, we prefer using integer ids for this as opposed to matching Objects or String tags because the ids fit nicely into switch statements!

For example, a layout with two buttons, both pointing at the same action method:

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
> android:orientation="vertical"
> android:layout\_width="fill\_parent"
> android:layout\_height="fill\_parent">
> <Button
> > android:id="@+id/firstButton"
> > android:layout\_width="fill\_parent"
> > android:layout\_height="wrap\_content"
> > android:text="Click Me!"
> > android:onClick="onButtonClick"

> />
> <Button
> > android:id="@+id/secondButton"
> > android:layout\_width="fill\_parent"
> > android:layout\_height="wrap\_content"
> > android:text="Click Me Too!"
> > android:onClick="onButtonClick"

> />


Unknown end tag for &lt;/LinearLayout&gt;


We can determine which button was pressed in the Activity by checking the sender’s id:

public class MyActivity extends Activity {
> @Override
> public void onCreate(Bundle savedInstanceState) {
> > super.onCreate(savedInstanceState);
> > setContentView(R.layout.main);

> }

> public void onButtonClick(View v) {
> > switch(v.getId()) {
> > case R.id.firstButton:
> > > //Handle first button click
> > > break;

> > case R.id.secondButton:
> > > //Handle second button click
> > > break;

> > default:
> > > break;

> > }

> }
}
This is not breaking new, and is just one of many useful properties of android:id…
But what if we didn’t use XML to create our layouts/menus/etc.?  What if we created a series of Views in Java code?  Are we destined to go wanting without the assistance of the unique R.id generator?  Certainly not!

Making id Elements Yourself
To reserve a set of ids for your own use in application, simply create an ids.xml file in the res/values directory of your project.  The syntax coupled with creating this file is pretty self-explanatory

res/values/ids.xml
<?xml version="1.0" encoding="utf-8"?>


&lt;resources&gt;


> 

&lt;item type="id" name="firstButton"/&gt;


> 

&lt;item type="id" name="secondButton"/&gt;


> 

&lt;item type="id" name="thirdButton"/&gt;




&lt;/resources&gt;


Now let’s take these fresh ids and add them to a series Buttons created in a dynamic layout:

public class MyActivity extends Activity implements View.OnClickListener {
> @Override
> public void onCreate(Bundle savedInstanceState) {
> > super.onCreate(savedInstanceState);
> > LinearLayout layout = new LinearLayout(this);
> > layout.setOrientation(LinearLayout.VERTICAL);


> Button button;
> button = new Button(this);
> button.setOnClickListener(this);
> button.setText("Click Me First!");
> button.setId(R.id.firstButton);
> layout.addView(button);

> button = new Button(this);
> button.setOnClickListener(this);
> button.setText("Click Me Next!");
> button.setId(R.id.secondButton);
> layout.addView(button);

> button = new Button(this);
> button.setOnClickListener(this);
> button.setText("Then Click Me!");
> button.setId(R.id.thirdButton);
> layout.addView(button);

> setContentView(layout);
> }

> public void onClick(View v) {
> > switch(v.getId()) {
> > case R.id.firstButton:
> > > //Handle first button click
> > > break;

> > case R.id.secondButton:
> > > //Handle second button click
> > > break;

> > case R.id.thirdButton:
> > > //Handle third button click
> > > break;

> > default:
> > > break;

> > }

> }
}
That’s it!  Just another practical application of a little known resource type in the Android framework