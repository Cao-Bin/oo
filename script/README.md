# All build script.

ms-parent: 包括发布插件和spring管理相关配置

buildscript: 在buildScript block apply


--------------------------------
各个脚本内容说明：

    common-ext: 所有通用的ext 变量文件，只包含变量
    build-script: 微服务需要引用的第一个文件，包括常用的spring 插件和插件对应的仓库。依赖：
        common-ext.gradle
    bootApp: 微服务(基于spring boot)依赖的插件和通用依赖，已经docker任务。依赖：
          ms-common.gradle
          docker.gradle
    common: 通用脚本，包括部分maven仓库和发布任务，依赖：
            common-ext.gradle
            publish.gradle
            repository.gradle
    docker: 负责将可允许jar包装成对应的docker镜像。
    ms-common: 微服务相关的文件,包括一些微服务相关的插件已经依赖管理等。依赖：
             common.gradle
             springDependency.gradle
    publish:负责讲工程发布到maven私库。
    repository:配置一些通用的依赖仓库。
    springDependency:spring 的依赖管理相关。
