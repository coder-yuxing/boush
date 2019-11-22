# Demo

## 1. 配置文件 generate.properties

```
driver-class-name=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://172.16.228.138:3306/lucifer
username=root
password=Wge2I9RQZEZ9PJYQ
```



## 2. 运行

```java
public static void main(String[] args) throws Exception {
        TableConfig tableConfig = TableConfig.builder().tableName("uac_user").beanName("User").remarks("用户").build();
        GlobalConfig globalConfig = GlobalConfig.builder()
                .author("GuangRen")
                .datePattern("yyyy/MM/dd")
                .isCover(false)
                .beanModuleName("")
                .beanPackage("com.dabanjia.boush.dao.model")
                .mapperModuleName("")
                .beanMapperPackage("com.dabanjia.boush.dao.mapper")
                .mapperPackage("com.dabanjia.boush.dao.xml")
                .tableConfigs(Collections.singletonList(tableConfig)).build();

        new Generate(globalConfig).execute();

}
```

