<template>
  <div class="login-wrap">
    <el-form :model="registerUserInfo" :rules="rules" class="login-container" ref="registerForm">
      <h1 class="title">用户注册</h1>
      <el-form-item label="账号" prop="userName">
        <el-input v-model="registerUserInfo.userName" placeholder="注册账号" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="userPassword">
        <el-input type="password" v-model="registerUserInfo.userPassword" show-password placeholder="注册密码" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input type="password" v-model="registerUserInfo.confirmPassword" show-password placeholder="请确认密码" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width:100%;" @click="register">注册</el-button>
      </el-form-item>
      <el-row style="text-align: center;margin-top:-10px">
        <el-button type="text" @click="gotoLogin">登录页面</el-button>
      </el-row>
    </el-form>
  </div>
</template>

<script>
  import axios from 'axios';
  export default {
    name: "Register",
    data() {
      return {
        registerUserInfo: {
          userName: '',
          userPassword: '',
          confirmPassword: ''
        }
      };
    },
    computed: {
      rules() {
        return {
          userName: [
            {required: true, message: '请输入账号', trigger: 'blur'},
            {min: 3, max: 20, message: '账号名需有3-20个字符', trigger: 'blur'}
          ],
          userPassword: [
            {required: true, message: '请输入密码', trigger: 'blur'}
          ],
          confirmPassword: [
            {required: true, message: '请再次输入密码', trigger: 'blur'},
            {validator: this.validatePass, trigger: 'blur'}
          ]
        };
      }
    },
    methods: {
      validatePass(rule, value, callback) {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.registerUserInfo.userPassword) {
          callback(new Error('两次输入的密码不一致!'));
        } else {
          callback();
        }
      },
      register() {
        const _self = this
        _self.$http.post("/api/register", this.registerUserInfo).then(resp => {
          if (resp.data.code == 200) {
            _self.$message.success(resp.data.msg)
            _self.$router.push('/login');
          } else _self.$message.error(resp.data.msg)
        })
      },
      gotoLogin() {
        const _self = this
        // 这里可以使用router跳转到登录页面
        _self.$router.push('/login');
      }
    }
  }




</script>

<style scoped>
  .login-wrap {
    box-sizing: border-box;
    min-height: 100vh;
    width: 100%;
    height: 100%;
    padding-top: 10%;
    background-image: url("../assets/register-background.jpg");
    background-size:contain;
    background-repeat: no-repeat;
    background-position: center right;
    background-size: 100%;
  }

  .login-container {
    border-radius: 10px;
    margin: 0px auto;
    top: 20px; /* 距离顶部一定距离 */
    right: 20px; /* 距离右边一定距离 */
    width: 350px;
    padding: 30px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    text-align: left;
    box-shadow: 0 0 20px 2px rgba(0, 0, 0, 0.1);
  }

  .title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
</style>
