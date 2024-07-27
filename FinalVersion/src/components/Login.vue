<template>
  <div class="login-page">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span class="login-title">图书管理系统</span>
      </div>
      <div class="login-form">
        <el-form :model="loginUserInfo" ref="">
          <el-form-item prop="">
            <el-input type="text" v-model="loginUserInfo.userName"  auto-complete="off" prefix-icon="el-icon-user-solid" placeholder="请输入用户名">
            </el-input>
          </el-form-item>
          <el-form-item prop="">
            <el-input type="password" v-model="loginUserInfo.userPassword"   auto-complete="off" prefix-icon="el-icon-lock" placeholder="请输入密码" show-password>
            </el-input>
          </el-form-item>

          <el-form-item>
            <el-button style="width:100%;" type="primary" @click="login">登录</el-button>
          </el-form-item>
          <el-row style="text-align: center;margin-top:-10px">
            <el-link type="primary" @click="gotoRegister">用户注册</el-link>
          </el-row>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>

  export default {
    name: "Login",
    data(){
      return{
        loginUserInfo:{
          userName: undefined,
          userPassword: undefined
        }
      }
    },
    methods:{
      gotoRegister() {
        this.$router.push("/Register");
      },
      login(){
        const _self = this
        //请求方式，基本的有两种：get、post请求    为了满足restful风格开发，还需要delete和put请求
        //get----->查询
        //post---->添加操作
        //put----->修改
        //delete----->删除操作
        //http://localhost:8088/user/1-----》delete
        //http://localhost:8088/user/query?userid=1
        //http://localhost:8088/user/remove?userid=1
        //发送登录请求
        _self.$http.post("/api/login",_self.loginUserInfo)
          .then(function (resp) {
            if(resp.data.code==500){
              _self.$message.error(resp.data.msg)
            }else if(resp.data.code==200){//登录成功,跳转到主页
              console.log(resp)
              //将当前登录账号存入sessionStorage
              sessionStorage.setItem("username",_self.loginUserInfo.userName)
              //将token存入sessionStorage
              sessionStorage.setItem("satoken",resp.data.data.token)
              //将头像存入sessionStorage
              sessionStorage.setItem("imgUrl",resp.data.data.imgUrl)
              sessionStorage.setItem("userid",resp.data.data.userId)
              _self.$router.replace("/index")
            }
        })
      }
    }
  }
</script>

<style scoped>
  .login-page{
    background-image: url("../assets/login-background.jpg");
    background-size: cover;
    /* 背景图片不重复 */
    background-repeat: no-repeat;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .login-title{
    font-size: 20px;
  }

  .box-card {
    width: 375px;
  }
</style>
