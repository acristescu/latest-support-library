# latest-support-library
An investigation into `ConstraintLayout`, `BottomNavigationView` and Data Binding for Android.
## Contraint Layout
### What is it?
Constraint Layout is a new layout system that allows one to create perfectly flat (as in not nested) architecture. Think of it as `RelativeLayout` on steroids. The developer specifies the position of each component relative to the edges of the other siblings or parent (e.g. `the top of this TextView is the same as the bottom of this ImageView`) and the layout manager turns those constraints into a linear equations system, solves it and determines the positions of the items in the parent view.

The Constraint Layout is meant to be used in conjunction with the Layout Editor tool from Android Studio. This is due to the complexity of these constraints parameters (there are no less than 26 these!). As such, learning the new Constraint Layout implies learning to use the Layout Editor as well. This in itself will probably constitute the biggest obstacle in the adoption of the new layout, as Android developers are not generally used to drag-and-drop view editing.

### Compatibility
The library is supported back to API 9.

### Example
The [song item](app/src/main/res/layout/item_song.xml) XML uses the `ConstraintLayout`.

### Advantages and disadvantages
Compared to other layouts (e.g. `LinearLayout` and `RelativeLayout`) the `ConstraintLayout` should be more efficient for very complex layouts, but writing the layout itself seems to require a bit more work.

Advantages:
* Flat structure is faster to layout
* XML is somewhat cleaner, consisting mostly of the views that hold the data
* Blueprint viewer helps with understanding complex layouts that a developer sees for the first time.
* Some previously complicated requirements are now easier to implement, for example:
  * Aligning two TextViews so that the baseline of their text matches
  * Aligning anything with the baseline of a `TextView`
  * Having a view partially overlap two other views (e.g. a FAB that sits only half way over the header image).

Disadvantages:
* Steep learning curve
* Dependency on the Layout Editor
* At the time of this writing (Jan 2016), the Layout Editor has bugs, causing random constraints to disappear, or the preview window to be incorrect
* uncertain if it will ever be fully adopted
* requires a bit of an effort to setup Jenkins to work with it. The solution I've found for a previous project relied on the commands listed below (but note the contents of the file may or may not change with the versions of the library):
```sh
mkdir "$ANDROID_SDK/licenses"
echo -e "\n8933bad161af4178b1185d1a37fbf41ea5269c55" > "$ANDROID_SDK/licenses/android-sdk-license"
```
### Recommendation
The library has the potential to become very useful in the future, but in the current state, and due to the steep learning curve, I recommend not using it except in very complex layouts where speed of rendering is an issue (e.g. RecyclerView with many, potentially complex layout items - such as the Facebook app for example).
