LoadingEverywhere [![Build Status](https://travis-ci.org/lsjwzh/loadingeverywhere.png)](https://travis-ci.org/lsjwzh/loadingeverywhere)
=================
LoadingEverywhere is an Android library that makes it easy to create and manage a loading mask over any view. 

LoadingEverywhere actively supports android versions 2.3 (gingerbread) and above.

Installing
---------------
###Maven
Add the following maven dependency exchanging `x.x.x` for the latest release.
```XML
<dependency>
    <groupId>com.lsjwzh</groupId>
    <artifactId>loadingeverywhere</artifactId>
    <version>x.x.x</version>
</dependency>
```

###Gradle
Add the following gradle dependency exchanging `x.x.x` for the latest release.
```groovy
dependencies {
    compile 'com.lsjwzh:loadingeverywhere:x.x.x'
}
```

####Note that the latest release version is '2.0.5'

###Cloning
First of all you will have to clone the library.
```shell
git clone https://github.com/lsjwzh/loadingeverywhere.git
```

Now that you have the library you will have to import it into Android Studio.
In Android Studio navigate the menus like this.
```
File -> Import Project ...
```
In the following dialog navigate to LoadingEverywhere which you cloned to your computer in the previous steps and select the `build.gradle`.

Getting Started
---------------
###Base usage

####LoadingLayout

Ok lets start with your activities or fragments xml file. It might look something like this.
```xml
<com.lsjwzh.loadingeverywhere.LoadingLayout
    android:id="@+id/loading"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

Also in your activities `onCreate()` or your fragments `onCreateView()` you would want to wrap a LoadingLayout like this
```java
LoadingLayout loadingLayout = LoadingLayout.wrap(findViewById(R.id.list));
```
`LoadingLayout.wrap` method can be called any where.
Because LoadingLayout will replace the view's position until the view has been attach to view tree.

You can also use custom progressBar style in LoadingLayout like this:
```java
LoadingLayout loadingLayout = LoadingLayout.wrap(findViewById(R.id.list),android.R.attr.progressBarStyleLarge);
```

####ProgressLayout
ProgressLayout has the same usage as LoadingLayout above.
Xml Define:
```xml
<com.lsjwzh.loadingeverywhere.ProgressLayout
    android:id="@+id/progress"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```
code:
```java
ProgressLayout progressLayout = ProgressLayout.wrap(findViewById(R.id.list));
```

```java
ProgressLayout progressLayout = ProgressLayout.wrap(findViewById(R.id.list),android.R.attr.progressBarStyleLarge);
```

####MaskLayout

####OverlayLayout


ToDo List
---------
1. ~~GenericStatusLayout~~
1. animation support
1. add more progressBar Style
1. detail usage
1. demo apk
1. auto fit view‘s width and height
1. button with loading  


Contributing
------------
Contributions are very welcome. Now that this library has grown in popularity i have a hard time keeping upp with all the issues while tending to a multitude of other projects as well as school. So if you find a bug in the library or want a feature and think you can fix it yourself, fork + pull request and i will greatly appreciate it!

I love getting pull requests for new features as well as bugs. However, when it comes to new features please also explain the use case and way you think the library should include it. If you don't want to start coding a feature without knowing if the feature will have chance of being included, open an issue and we can discuss the feature!


License
-------

    Copyright 2014 lsjwzh

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
