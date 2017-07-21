
  -optimizationpasses 5
  -ignorewarnings                     # 忽略警告，避免打包时某些警告出现
#  
   -keep class com.admai.sdk.**{*;}
   -keep class com.a.a.**{*;} 
   
#  proguard mapping
  -printmapping build/outputs/mapping/release/mapping.txt
 # 不启用大小写
  -dontusemixedcaseclassnames
 #keep lineNum
 -keepattributes SourceFile,LineNumberTable

#缺省proguard 会检查每一个引用是否正确，但是第三方库里面往往有些不会用到的类，没有正确引用。如果不配置的话，系统就会报错。
 -dontwarn android.support.v4.**     

 -keepattributes *Annotation*
 -keepattributes JavascriptInterface
 -keepattributes InnerClasses
 -keepattributes Exceptions
 -keepattributes Signature

##keep bean
# -keep public class com.admai.maiweather.modules.main.bean.**{*;}
 
#view
 -keepclasseswithmembers class * {
     public <init>(android.content.Context,android.util.AttributeSet);
 }

 -keepclasseswithmembers class * {
     public <init>(android.content.Context,android.util.AttributeSet,int);
 }

#
 -keepclassmembers class * extends android.app.Activity {
     public void *(android.view.View);
 }

 -keep class * extends android.os.Parcelable {
     public static final android.os.Parcelable$Creator *;
 }

 # Keep - Applications. Keep all application classes, along with their 'main'
 # methods.
 -keepclasseswithmembers public class * {
     public static void main(java.lang.String[]);
 }

 # Also keep - Database drivers. Keep all implementations of java.sql.Driver.
 -keep class * extends java.sql.Driver

 # Also keep - Swing UI L&F. Keep all extensions of javax.swing.plaf.ComponentUI,
 # along with the special 'createUI' method.
 -keep class * extends javax.swing.plaf.ComponentUI {
     public static javax.swing.plaf.ComponentUI createUI(javax.swing.JComponent);
 }

 # Keep names - Native method names. Keep all native class/method names.
 -keepclasseswithmembers,includedescriptorclasses,allowshrinking class * {
     native <methods>;
 }

 ############################################################################
 -keep class android.support.design.** { *;}
 -keep public class * extends android.app.Activity
 -keep public class * extends android.app.Application
 -keep public class * extends android.app.Service
 -keep public class * extends android.content.BroadcastReceiver
 -keep public class * extends android.content.ContentProvider
 -keep public class * extends android.app.backup.BackupAgentHelper
 -keep public class * extends android.preference.Preference
 -keep public class * extends android.view.View
 -keep public class com.android.vending.licensing.ILicensingService
 -keep class android.support.** {*;}
 #保护注解
 -keepattributes *Annotation*
 -dontwarn java.lang.invoke.*
 -keep class butterknife.** { *; }
 -dontwarn butterknife.internal.**
 -keep class **$$ViewBinder { *; }
 -keepclasseswithmembernames class * {
     @butterknife.* <fields>;
 }
 -keepclasseswithmembernames class * {
     @butterknife.* <methods>;
 }
 -dontwarn okio.**
 
 #-Keep the fields annotated with @Inject of any class that is not deleted.
 -keepclassmembers class * {
   @javax.inject.* <fields>;
 }
 #-Keep the names of classes that have fields annotated with @Inject and the fields themselves.
 -keepclasseswithmembernames class * {
   @javax.inject.* <fields>;
 }
 
 
 ############################################################################
 
 # Android support v7
 -keep public class android.support.v7.widget.** { *; }
 -keep public class android.support.v7.internal.widget.** { *; }
 -keep public class android.support.v7.internal.view.menu.** { *; }
 
 -keep public class * extends android.support.v4.view.ActionProvider {
     public <init>(android.content.Context);
 }
 
 # Guava:
 -dontwarn javax.annotation.**
 -dontwarn javax.inject.**
 -dontwarn sun.misc.Unsafe
 
 # butterknife
 -keep class butterknife.** { *; }
 -dontwarn butterknife.internal.**
 -keep class **$$ViewBinder { *; }
 -keepclasseswithmembernames class * {
     @butterknife.* <fields>;
 }
 -keepclasseswithmembernames class * {
     @butterknife.* <methods>;
 }
 
 # retrofit
 -dontwarn retrofit2.**
 -keep class retrofit2.** { *; }
 -keepattributes Signature
 -keepattributes Exceptions
 
 # gson
 -keepattributes Signature
 -keepattributes *Annotation*
 -keepattributes EnclosingMethod
 -keep class sun.misc.Unsafe { *; }
 -keep class com.google.gson.stream.** { *; }
 
 # RxJava:
 -dontwarn org.mockito.**
 -dontwarn org.junit.**
 -dontwarn org.robolectric.**
 -keep class rx.schedulers.Schedulers {
     public static <methods>;
 }
 -keep class rx.schedulers.ImmediateScheduler {
     public <methods>;
 }
 -keep class rx.schedulers.TestScheduler {
     public <methods>;
 }
 -keep class rx.schedulers.Schedulers {
     public static ** test();
 }
 -keep class rx.internal.util.unsafe.** { *; }
 
 # 枚举需要keep see http://proguard.sourceforge.net/manual/examples.html#enumerations
 -keepclassmembers enum * {
     **[] $VALUES;
     public *;
 }
# 
 -keep class me.henrytao.smoothappbarlayout.** { *; }
# glide
 -keep public class * implements com.bumptech.glide.module.GlideModule
 -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** { 
   **[] $VALUES;
   public *;
 }
 
 #保持 Serializable 不被混淆
 -keepnames class * implements java.io.Serializable
 -keep class * implements java.io.Serializable { *; }
# 
 -keepclassmembers class * implements java.io.Serializable {
     static final long serialVersionUID;
     private static final java.io.ObjectStreamField[] serialPersistentFields;
     !static !transient <fields>;
     !private <fields>;
     !private <methods>;
     private void writeObject(java.io.ObjectOutputStream);
     private void readObject(java.io.ObjectInputStream);
     java.lang.Object writeReplace();
     java.lang.Object readResolve();
 }
 
# rxbus
 -keepattributes *Annotation*
 -keepclassmembers class ** {
     @com.hwangjr.rxbus.annotation.Subscribe public *;
     @com.hwangjr.rxbus.annotation.Produce public *;
 }
  
  # 使用注解
#  -keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod

#CityORM 
 -keep class com.admai.maiweather.modules.main.domain.CityORM{*;}
 -keepclasseswithmembernames class com.admai.maiweather.modules.main.domain.CityORM{*;}
 

