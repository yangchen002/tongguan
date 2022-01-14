# tongguan

非对称加密指的是使用公钥和私钥来进行加密解密操作。对于加密操作，公钥负责加密，私钥负责解密，对于签名操作，私钥负责签名，公钥负责验证。非对称加密在JWT中的使用显然属于签名操作。

如果我们需要使用固定的公钥和私钥来进行签名和验证的话，我们需要生成一个证书文件，这里将使用Java自带的keytool工具来生成jks证书文件，该工具在JDK的bin目录下；

打开CMD命令界面，使用如下命令生成证书文件，设置别名为jwt，文件名为jwt.jks；

keytool -genkey -alias jwt -keyalg RSA -keystore jwt.jks
