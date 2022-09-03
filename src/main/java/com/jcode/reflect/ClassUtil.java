package com.jcode.reflect;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {
	 private static final String CLASS_SUFFIX = ".class";
	 
	     private static final String CLASS_FILE_PREFIX = File.separator + "classes"  + File.separator;
	 
	     private static final String PACKAGE_SEPARATOR = ".";
	 
	     
	 
	     
	 
	     
	 
	     
	 
	     /**
	 
	      * 查找包下的所有类的名字
	
	      * @param packageName
	 
	      * @param showChildPackageFlag 是否需要显示子包内容
	 
	      * @return List集合，内容为类的全名
	 
	      */
	 
	     public static List<String> getClazzName(String packageName, boolean showChildPackageFlag ) {
	 
	         List<String> result = new ArrayList<>();
	 
	         String suffixPath = packageName.replaceAll("\\.", "/");
	 
	         ClassLoader loader = Thread.currentThread().getContextClassLoader();
	 
	         try {
	 
	             Enumeration<URL> urls = loader.getResources(suffixPath);
	 
	             while(urls.hasMoreElements()) {
	 
	                 URL url = urls.nextElement();
	 
	                 if(url != null) {
	 
	                     String protocol = url.getProtocol();
	 
	                     if("file".equals(protocol)) {
	 
	                         String path = url.getPath();
	 
	                         System.out.println(path);
	 
	                         result.addAll(getAllClassNameByFile(new File(path), showChildPackageFlag));
	 
	                     } else if("jar".equals(protocol)) {
	 
	                         JarFile jarFile = null;
	 
	                         try{
	                        	 
	                        	
	 
	                             jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
	 
	                         } catch(Exception e){
	 
	                             e.printStackTrace();
	 
	                         }
	 
	                         if(jarFile != null) {
	 
	                             result.addAll(getAllClassNameByJar(jarFile, packageName, showChildPackageFlag));
	 
	                         }
	 
	                     }
	 
	                 }
	 
	             }
	 
	         } catch (IOException e) {
	 
	             e.printStackTrace();
	 
	         }
	 
	         return result;
	 
	     }
	 
	     
	 
	     
	 
	     /**
	 
	      * 递归获取所有class文件的名字
	 
	      * @param file 
	 
	      * @param flag  是否需要迭代遍历
	 
	      * @return List
	 
	      */
	 
	     private static List<String> getAllClassNameByFile(File file, boolean flag) {
	 
	         List<String> result =  new ArrayList<>();
	 
	         if(!file.exists()) {
	 
	             return result;
	 
	         }
	 
	         if(file.isFile()) {
	 
	             String path = file.getPath();
	 
	             // 注意：这里替换文件分割符要用replace。因为replaceAll里面的参数是正则表达式,而windows环境中File.separator="\\"的,因此会有问题
	 
	             if(path.endsWith(CLASS_SUFFIX)) {
	 
	                 path = path.replace(CLASS_SUFFIX, "");
	 
	                 // 从"/classes/"后面开始截取
	 
	                 String clazzName = path.substring(path.indexOf(CLASS_FILE_PREFIX) + CLASS_FILE_PREFIX.length())
	 
	                         .replace(File.separator, PACKAGE_SEPARATOR);
	 
	                 if(-1 == clazzName.indexOf("$")) {
	 
	                     result.add(clazzName);
	 
	                 }
	 
	             }
	 
	             return result;
	 
	             
	 
	         } else {
	 
	             File[] listFiles = file.listFiles();
	 
	            if(listFiles != null && listFiles.length > 0) {
	 
	                 for (File f : listFiles) {
	 
	                     if(flag) {
	 
	                         result.addAll(getAllClassNameByFile(f, flag));
	 
	                     } else {
	 
	                         if(f.isFile()){
	 
	                             String path = f.getPath();
	
	                             if(path.endsWith(CLASS_SUFFIX)) {
	 
	                                 path = path.replace(CLASS_SUFFIX, "");
	 
	                                 // 从"/classes/"后面开始截取
	 
	                                 String clazzName = path.substring(path.indexOf(CLASS_FILE_PREFIX) + CLASS_FILE_PREFIX.length())
	 
	                                         .replace(File.separator, PACKAGE_SEPARATOR);
	 
	                                 if(-1 == clazzName.indexOf("$")) {
	 
	                                     result.add(clazzName);
	 
	                                 }
	 
	                             }
	 
	                         }
	 
	                     }
	 
	                 }
	 
	             } 
	 
	             return result;
	 
	         }
	 
	     }
	 
	     
	 
	     
	 
	     /**
	
	      * 递归获取jar所有class文件的名字
	
	      * @param jarFile 
	
	      * @param packageName 包名
	
	      * @param flag  是否需要迭代遍历
	
	      * @return List
	 
	      */
	 
	     private static List<String> getAllClassNameByJar(JarFile jarFile, String packageName, boolean flag) {
	 
	         List<String> result =  new ArrayList<>();
	
	         Enumeration<JarEntry> entries = jarFile.entries();
	
	         while(entries.hasMoreElements()) {
	
	             JarEntry jarEntry = entries.nextElement();
	
	             String name = jarEntry.getName();
	 
	             // 判断是不是class文件
	 
	             if(name.endsWith(CLASS_SUFFIX)) {
	 
	                 name = name.replace(CLASS_SUFFIX, "").replace("/", ".");
	
	                 if(flag) {
	
	                     // 如果要子包的文件,那么就只要开头相同且不是内部类就ok
	
	                     if(name.startsWith(packageName) && -1 == name.indexOf("$")) {
	 
	                         result.add(name);
	
	                     }
	
	                 } else {
	 
	                     // 如果不要子包的文件,那么就必须保证最后一个"."之前的字符串和包名一样且不是内部类
	 
	                     if(packageName.equals(name.substring(0, name.lastIndexOf("."))) && -1 == name.indexOf("$")) {
	
	                         result.add(name);
	 
	                     }
	
	                 }
	 
	             }
	 
	         }
	 
	         return result;
	 
	   }
	     private static Set<Class<?>> classes = null;
	     
	     /**
	      * get All classes
	      *
	      * @param packageName
	      * @return
	      * @throws Exception
	      */
	     public static Set<Class<?>> getClasses(String packageName) throws Exception {

	         if (classes == null){
	             classes = new HashSet<>(32) ;

	             baseScanner(packageName,classes);
	         }

	         return classes;
	     }

	     private static void baseScanner(String packageName,Set set) {
	         boolean recursive = true;

	         String packageDirName = packageName.replace('.', '/');

	         Enumeration<URL> dirs;
	         try {
	             dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
	             while (dirs.hasMoreElements()) {
	                 URL url = dirs.nextElement();
	                 String protocol = url.getProtocol();
	                 if ("file".equals(protocol)) {
	                     String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
	                     findAndAddClassesInPackageByFile(packageName, filePath, recursive, set);
	                 } else if ("jar".equals(protocol)) {
	                     JarFile jar;
	                     try {
	                         jar = ((JarURLConnection) url.openConnection()).getJarFile();
	                         Enumeration<JarEntry> entries = jar.entries();
	                         while (entries.hasMoreElements()) {
	                             JarEntry entry = entries.nextElement();
	                             String name = entry.getName();
	                             if (name.charAt(0) == '/') {
	                                 name = name.substring(1);
	                             }
	                             if (name.startsWith(packageDirName)) {
	                                 int idx = name.lastIndexOf('/');
	                                 if (idx != -1) {
	                                     packageName = name.substring(0, idx).replace('/', '.');
	                                 }
	                                 if ((idx != -1) || recursive) {
	                                     if (name.endsWith(".class") && !entry.isDirectory()) {
	                                         String className = name.substring(packageName.length() + 1, name.length() - 6);
	                                         try {
	                                             set.add(Class.forName(packageName + '.' + className));
	                                         } catch (ClassNotFoundException e) {
	                                             e.printStackTrace();
	                                         }
	                                     }
	                                 }
	                             }
	                         }
	                     } catch (IOException e) {
	                         //LOGGER.error("IOException", e);
	                     }
	                 }
	             }
	         } catch (IOException e) {
	             //LOGGER.error("IOException", e);
	         }
	     }


	     public static void findAndAddClassesInPackageByFile(String packageName,
	                                                         String packagePath, final boolean recursive, Set<Class<?>> classes) {
	         File dir = new File(packagePath);
	         if (!dir.exists() || !dir.isDirectory()) {
	             return;
	         }
	         File[] files = dir.listFiles(file -> (recursive && file.isDirectory())
	                 || (file.getName().endsWith(".class")));
	         for (File file : files) {
	             if (file.isDirectory()) {
	                 findAndAddClassesInPackageByFile(packageName + "."
	                                 + file.getName(), file.getAbsolutePath(), recursive,
	                         classes);
	             } else {
	                 String className = file.getName().substring(0,
	                         file.getName().length() - 6);
	                 try {
	                     classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
	                 } catch (ClassNotFoundException e) {
	                     //LOGGER.error("ClassNotFoundException", e);
	                 }
	             }
	         }
	     }

	 
	     
}
