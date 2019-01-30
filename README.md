[![](https://jitpack.io/v/AdminFun/ScoreView.svg)](https://jitpack.io/#AdminFun/ScoreView)
这是一个卡片文字控件，我暂时把它命名为计分器。

### 一、效果
![效果图](http://projectdoc.epetbar.com/Public/Uploads/2019-01-29/5c50185d8b9d3.png "效果图")

### 二、使用

##### 1、XML使用
```
<com.fun.widget.scroeview.ScoreView
    android:id="@+id/tag_transition_group"
    android:layout_width="150dp"
    android:layout_height="100dp"
    android:background="#9A32CD"
    android:gravity="center"
    app:score_horizontal_space="5dp"
    app:item_background_color="#FF7F50"
    app:item_border_color="#D9D9D9"
    app:item_border_width="0px"
    app:item_corner_radius="3dp"
    app:item_horizontal_padding="10dp"
    app:item_text_color="@android:color/white"
    app:item_text_size="50sp"
    app:item_vertical_padding="8dp"
    app:score_text="99" />
```

##### 2、CODE使用
```
ScoreView scoreView = findViewById(R.id.scoreView);
        scoreView.setHorizontalInterval(5);
        // 这里省略属性SET
        scoreView.setText("99");
```



### 三、属性
|名称   |类型   |描述   |
| ------------ | ------------ | ------------ |
|score_horizontal_space   |dimension   |卡片之间的横向间距   |
|score_text   |string   |计分器要显示的文本，例如：99   |
|item_horizontal_padding   |dimension   |卡片内部的横向间距，单个字符两边的间距距离   |
|item_vertical_padding   |dimension   |卡片内部的纵向间距，单个字符上下的间距距离   |
|item_background_color   |color   |单个卡片的背景色   |
|item_background   |reference   |单个卡片的背景   |
|item_border_width   |dimension   |单个卡片的边框宽度   |
|item_border_color   |color   |单个卡片的边框颜色   |
|item_corner_radius   |dimension   |单个卡片的边框圆角角度   |
|item_text_size   |dimension   |文本字体大小   |
|item_text_color   |color   |文本字体颜色   |
|item_text_style   |color   |文本字体样式   |

### 四、版本
##### 1、v1.0
初版