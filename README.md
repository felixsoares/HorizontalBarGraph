[![Release]( https://img.shields.io/badge/Release-v1.0-blue.svg?style=flat )]( https://jitpack.io/#felixsoares/HorizontalBarGraph/ )

# Horizontal Bar Graph

### Getting Started

Add it in your root build.gradle (Project module)

```gradle
allprojects {
   repositories {
        ...
        maven { url 'https://jitpack.io' }
   }
}
```

Add the dependency in build.gradle (App module)

```gradle
dependencies {
	compile 'com.github.felixsoares:HorizontalBarGraph:1.0'
}
```

## Usage example

In layout file

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <br.com.felix.horizontalbargraph.HorizontalBar
        android:id="@+id/horizontal"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</RelativeLayout>
```

In Activity or Fragment

```java
HorizontalBar horizontal = findViewById(R.id.horizontal);
horizontal.init(this).build();
```

### Documentation

1) Add itens or single bar item

```java
HorizontalBar horizontal = findViewById(R.id.horizontal);
horizontal.init(this).addAll(itens()).addOne(new BarItem("yyy", 1000d)).build();
```

or

```java
HorizontalBar horizontal = findViewById(R.id.horizontal);
horizontal.init(this).build();

horizontal.addAll(itens());
horizontal.add(new BarItem("yyy", 1000d));
```

2) Support Animation

```java
HorizontalBar horizontal = findViewById(R.id.horizontal);
horizontal.init(this).hasAnimation(true).addAll(itens()).build()
```

3) Colors

```java
List<BarItem> items = new ArrayList<>();

//Will generate the bar colors and text colors automatically randomly
items.add(new BarItem("ABC", 250d));	
items.add(new BarItem("ABCD", 800d, 550d));

//Set colors from bar and text foreground
//                                 bar color, text color
items.add(new BarItem("ABC", 250d, Color.RED, Color.WHITE));	

//				          first bar color, second bar color, first text color, second text color
items.add(new BarItem("Teste " + i, 800d, 600d, Color.RED, Color.GREEN, Color.WHITE, Color.WHITE));
```

4) Remove itens

```java
horizontal.removeOne(position);
```

or

```java
horizontal.removeAll();
```

<br/>

MIT License

Copyright (c) [year] [fullname]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
