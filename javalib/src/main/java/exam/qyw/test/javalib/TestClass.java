package exam.qyw.test.javalib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * Created by Author:qyw
 * on 2018/11/23.
 * QQ:448739075
 * √Ë ˆ£∫
 */
public class TestClass {
    public static void main(String[] args) {
        Stack<String> stringStack=new Stack<>();
        Queue<String> queue=new ArrayDeque<>();
        queue.add("1");
        queue.add("2");

        stringStack.add("1");
        stringStack.add("2");
        System.out.println(((ArrayDeque<String>) queue).peekFirst());
    }
}
