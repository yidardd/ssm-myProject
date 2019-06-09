# myProject
分模块的ssm项目,只修改了web,admin同web.

主要六个模块:
web 表示前台项目,或者手机端,h5端
admin 表示后台管理项目
service 服务层,主要引用dao层
dao 持久层,对数据库进行操作
common 主要的,通用的一些对象或者工具类等等
domain 放各种对象.

然后common里面引用domain,然后dao里面引用common,根据依赖传递,这样就能够正常开始写项目啦!

