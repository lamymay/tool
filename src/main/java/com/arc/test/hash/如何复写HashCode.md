
```$text

1.比较对象是不是一样的？ hashCode equals ==
要从物理上判断2个对象是否相等，用==就可以了。
hashCode相 当于是一个对象的编码，就好像文件中的md5，
hashCode和equals不同就在于他返回的是int型的，比较起来不直观。我们一般在覆盖equals的同时也要 覆盖hashcode，让他们的逻辑一致。
举个例子，还是刚刚的例子，如果姓名和性别相等就算2个对象相等的话，那么hashcode的方法也要返回姓名 的hashcode值加上性别的hashcode值，这样从逻辑上，他们就一致了。 


2. 如何重写 hashCode()方法
将所有需要参与计算的属性值都合并成一个字符串，然后转换成一个字符数组：

    //char[] charArr = sb.toString().toCharArray();然后遍历这个字符数组进行计算。
    @Override
    public int hashCode() {
        //return super.hashCode();
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(age);
        char[] charArr = sb.toString().toCharArray();

        int hash = 0;
        for (char c : charArr) {
            hash = hash * 131 + c;
        }
        return hash;
    }


3. 复写equals的同时也要复写hashCode方法的原因
让equals与hashCode比较对象相同时候逻辑的逻辑一致,即：Object.hashCode的通用约定，声明相等对象相等必须具有相等的哈希码，否则可能导致该类无法结合所有基于散列的集合一起正常运作，这样的集合包括HashMap、HashSet和Hashtable
例如集合HashSet里真正存放元素的逻辑是在putVal()这个方法中，这里面代码较多就不贴上来了，这里简述一下其中的关键逻辑。它会调用存入元素的hashCode()方法，计算出元素所对应在表中的位置，然后判断这个位置上是否已经有内容了。如果这个位置上以及有了一个元素，那么就调用传入元素的equals()方法与已有的元素进行对比，以此来判断两个元素是否相同，如果不相同，就将这个元素也存入表中。



4. 有什么用？
equals()方法的集合中存取时候的使用hashCode()方法确定元素在数据结构中存放的位置。而使用equals()来确认当两个元素存放的位置发生冲突时，是应该将两个元素都存入数据结构，还是说只需要存放其中一个。


你当然可以不按要求去做了，但你会发现，相同的对象可以出现在Set集合中。同时，增加新元素的效率会大大下降。hashcode这个方法是用来鉴定2个对象是否相等的。 那你会说，不是还有equals这个方法吗？ 不错，这2个方法都是用来判断2个对象是否相等的。但是他们是有区别的。 一般来讲，equals这个方法是给用户调用的，如果你想判断2个对象是否相等，你可以重写equals方法，然后在代码中调用，就可以判断他们是否相等 了。简单来讲，equals方法主要是用来判断从表面上看或者从内容上看，2个对象是不是相等。举个例子，有个学生类，属性只有姓名和性别，那么我们可以 认为只要姓名和性别相等，那么就说这2个对象是相等的。 hashcode方法一般用户不会去调用，比如在hashmap中，由于key是不可以重复的，他在判断key是不是重复的时候就判断了hashcode 这个方法，而且也用到了equals方法。这里不可以重复是说equals和hashcode只要有一个不等就可以了！

Java对于eqauls方法和hashCode方法是这样的注释： 
1、如果两个对象相同，那么它们的hashCode值一定要相同；
2、如果两个对象的hashCode相同，它们并不一定相同 上面说的对象相同指的是用eqauls方法比较。 


```

感谢：https://www.cnblogs.com/weilu2/p/java_hashcode_equals.html
