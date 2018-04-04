# 用于thymeleaf的Shiro扩展标签
## 功能描述
#### 用于在页面模板中应用Shiro框架提供的功能，例如权限验证、角色判断、提取用户凭证信息等
## 开发环境 
* 运行环境：Jvm
* 语言支持：Java、Kotlin、Groovy等所有基于Jvm的语言
* 框架依赖：Spring/Spring Boot、Thymeleaf、Shiro
## 项目结构

## Samples
> 1.启用扩展
* 可以使用@Bean注解或配置方式来启用扩展方言
* 例如在配置类中：
````
@Configuration
public class MyConfiguration{
   @Bean
   fun shiroDialect(): ShiroDialect {
       return ShiroDialect()
   }
}
````
* 或者在配置文件中：
````
<bean id="shiroDialect" class="org.dubar.thymeleaf.extags.shiro.dialect.ShiroDialect" />
````
> 2.在thymeleaf模板文件中使用扩展标签
>> 2.1 Attributes
>>> 2.1.1 authenticated
*  语法：在任何Element中加入 sh:authenticated="boolean" 属性，用于限定该Elements是在登录或未登录的状态下才显示。例如：
````
<div sh:authenticated="true">
这里的内容将会在登录后显示
</div>
````
* 或者：
````
<div sh:authenticated="false">
这里的内容将会在未登录时显示
</div>
````
>> 2.1.2 role
*  语法：在任何Elements中加入 sh:role="role1;role2..rolen" 属性，可同时使用 mode="ANY|ALL|LACK|LACKALL" 属性用于指定role的匹配模式，只在指定的rolenames与mode相匹配时该Element才会在前端呈现（或不呈现）。例如：
````
<div sh:role="role1;role2" mode="ANY">
这里只在role1或role2两种角色的用户登录后才显示
</div>
````
*  或者：
````
<div sh:role="role1;role2" mode="ALL">
这里只在同时具有role1和role2双重角色用户登录后才显示
</div>
````
* 或者：
````
<div sh:role="role1;role2" mode="LACK">
这里的内容在以role1或role2身份登录时将不显示
</div>
````
* 或者：
````
<div sh:role="role1;role2" mode="LACKALL">
这里的内容在以role1和role2双重身份登录时将不显示
</div>
````
>> 2.1.3 user
*  语法：在任何Elements中加入 sh:user="user1;user2..usern" 属性，可同时使用 lacked="true"来指定是否采用缺失匹配（即当登录用否不在指定的用户列表中才显示本内容，默认为false，即登录用户在指定用户列表中才显示本内容）。例如：
````
<div sh:user="user1;user2">
<!--等效于<div sh:user="user1;usr2" lacked="false">-->
这里的内容只在登录用户名为user1或user2时才会显示
</div>
````
* 或者：
````
<div sh:user="user1;user2" lacked="true">
这里的内容只在登录用户名不为user1或user2时才会显示
</div>
````
* 如果向Shiro框架提供的身份信息不是基本数据类型（通常为String)，而是一个封装的用户对象，则需要用户property指定用于比较的属性名称：
````
<div sh:user="user1;user2" property="username">
这里的内容只在提交的身份对象的username属性值为user1或user2时才会显示
</div>
````
>> 2.1.4 permission
*  语法：在任何Elements中加入 sh:permission="perm1;perm2..permn" 属性，可同时使用 mode="ANY|ALL|LACK|LACKALL" 属性用于指定权限的匹配模式，只在指定的permissions与mode相匹配时该Element才会在前端呈现（或不呈现）。例如：
````
<div sh:permission="perm1;perm2" mode="ANY">
这里的内容只在当前用户拥有perm1或perm2两种权限之一时才显示
</div>
````
* 或者：
````
<div sh:permission="perm1;perm2" mode="ALL">
这里的内容只在当前用户同时拥有perm1、perm2两种权限时才显示
</div>
````
* 或者：
````
<div sh:permission="perm1;perm2" mode="LACK">
这里的内容只在当前用户不拥有perm1或perm2权限时才显示
</div>
````
* 或者：
````
<div sh:permission="perm1;perm2" mode="LACKALL">
这里的内容只在当前用户同时不拥有perm1和perm2两种权限时才显示
</div>
````
> 2.2 Elements
>> 2.2.1 principal
* 语法：在HTML模板文件的任意位置加入principal Element即可在当前位置呈现当前用户信息，例如：
````
<body>
    <label>您好<sh:principal/></label><!--在当前位置显示用户名称-->
</body>
````
* 如果提供Shiro框架的用户信息不是基本类型（通常为String）而是封装的某一对象（例如User对象）,需要用property属性来指定显示的属性名：
```
<body>
    <label>您好<sh:principal property="nickname"/></label><!--在当前位置显示用户昵称-->
</body>
```
## 作者列表
## 更新链接
## 历史版本
* 2018/04/04 1.0-SNAPSHOT
## 联系方式
* mail:edenyin@126.com
* qq:87803425