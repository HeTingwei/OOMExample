# OOMExaple
基于recyclerview和Bitmap加载大量图片时会消耗内存,如果不及时清理的话，会产生OOM的错误。
Bitmap类有一个recycle()方法可以回收内存，但是回收后的子项滑动到眼前，有会因为图像资源被回收而出现错误。<br>
所以在加载到眼前前，又要重新给Imageview加载一个有资源的bitmap.<br>
需要注意的是:<br>
适配器中的onBindViewHolder在让子项出现在眼前时均会调用，即使之前已经出现在眼前过，但是滑动回来又会调用。<br>
下面的代码在MainActivity中：目的是使即将进入视野的子项重新加载，退出后的子项回收bitmap<br>
```
 //上拉
                if (firstOld < firstNew) {
                    firstOld++;
                    itemAdapter.release(firstNew - 2);
                    itemAdapter.resetItem(firstNew + visibleCount);
                }
                //下拉
                if (firstOld > firstNew) {
                    firstOld--;
                    itemAdapter.resetItem(firstNew - 1);
                    itemAdapter.release(firstNew + visibleCount + 1);
                }
```
效果如下，滑动时内存占有未变：<br>
![](https://github.com/HeTingwei/OOMExaple/blob/master/doc/%E6%95%88%E6%9E%9C.gif)
