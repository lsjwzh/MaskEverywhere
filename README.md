LoadingEverywhere
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

Ok lets start with your activities or fragments xml file. It might look something like this.
```xml
<com.lsjwzh.loadingeverywhere.LoadingLayout
    android:id="@+id/loading"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

Now in your activities `onCreate()` or your fragments `onCreateView()` you would want to do something like this
```java
LoadingLayout loadingLayout = LoadingLayout.wrap(findViewById(R.id.list));
```
`LoadingLayout.wrap` method can be called any where.
Because LoadingLayout will replace the view's position until the view has been attach to view tree.


Contributing
------------
Contributions are very welcome. Now that this library has grown in popularity i have a hard time keeping upp with all the issues while tending to a multitude of other projects as well as school. So if you find a bug in the library or want a feature and think you can fix it yourself, fork + pull request and i will greatly appreciate it!

I love getting pull requests for new features as well as bugs. However, when it comes to new features please also explain the use case and way you think the library should include it. If you don't want to start coding a feature without knowing if the feature will have chance of being included, open an issue and we can discuss the feature!
