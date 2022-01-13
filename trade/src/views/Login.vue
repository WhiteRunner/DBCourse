<template>
  <div class="login">
    <div class="index">
      <div class="picture hidden-sm-and-down">
        <div class="title">Second-hand Trade Platform</div>
        <img src="../assets/shopping.png" />
      </div>
      <div class="menu">
        <div class="title-small hidden-md-and-up">Second-hand Trade Platform</div>
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>{{ isRegisterBtnClick == false ? "Login" : "Register" }}</span>
            <el-button @click="registerBtn()" style="float: right; padding: 3px 0" type="text">
                {{isRegisterBtnClick == false ? "Register" : "Login"}}
            </el-button>
          </div>
          <!-- 登录面板 -->
          <el-form
            :model="user"
            label-width="80px"
            v-if="isRegisterBtnClick == false"
          >
            <el-form-item label="Username">
              <el-input
                v-model="user.username"
                placeholder="Please input username"
              ></el-input>
            </el-form-item>
            <el-form-item label="Password">
              <el-input
                v-model="user.password"
                placeholder="Please input password"
                type="password"
                show-password
              ></el-input>
            </el-form-item>
            <!-- 验证码功能，待开启 -->
            <!-- <el-form-item label="验证码">
              <el-input
                v-model="user.verification"
                placeholder="请输入验证码"
              ></el-input>
            </el-form-item> -->
            <div class="option">
              <el-button type="primary" style="width: 120px" @click="login()">Login</el-button>
              <!-- 待开启功能：忘记密码 -->
              <!-- <router-link to="/lostPwd">忘记密码</router-link> -->
            </div>
          </el-form>
          <!-- 注册面板 -->
          <el-form
            :model="user"
            label-width="80px"
            v-if="isRegisterBtnClick == true"
          >
            <el-form-item label="Username">
              <el-input
                v-model="newUser.username"
                placeholder="Please input username"
              ></el-input>
            </el-form-item>
            <el-form-item label="Password">
              <el-input
                v-model="newUser.password"
                placeholder="Please input password"
                type="password"
                show-password
              ></el-input>
            </el-form-item>
            <el-form-item label="Confirm">
              <el-input
                v-model="newUser.confirmPwd"
                placeholder="Please input password again"
                type="password"
                show-password
              ></el-input>
            </el-form-item>
            <!-- 验证码功能，待开启 -->
            <!-- <el-form-item label="验证码">
              <el-input
                v-model="user.verification"
                placeholder="请输入验证码"
              ></el-input>
            </el-form-item> -->
            <div class="option">
              <el-button type="primary" style="width: 120px" @click="register()">Register</el-button>
            </div>
          </el-form>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isRegisterBtnClick: false, //注册按钮是否被点击（是否切换到注册面板）
      user: {
        // username: "xyy",
        // password: "123456",
        username: "",
        password: "",
        // verification: "",//验证码功能，待开启
      },
      newUser: {
        username: "",
        password: "",
        confirmPwd: "",
      },
    };
  },
  methods: {
    //登录功能
    login() {
      this.$axios
        .post("/User/login", this.user)
        .then((res) => {
          if (res.data.data != null) {
            console.log(res)
            this.$store.commit("setToken", res.data.data.tokenInfo.tokenValue);
            this.$store.commit("setUserId", res.data.data.id);
            // this.$store.commit("setSuperuser", res.data.data.superuser);
            this.$router.push("/home");
            this.$message({
              type: "success",
              message: "Welcome",
            });
          } else {
            this.$message.error("Error password or no such username");
          }
        })
        .catch(() => {
          this.$message.error("Server busy, please try again later");
        });
    },
    //点击注册按钮事件（切换到注册）
    registerBtn() {
      this.isRegisterBtnClick = !this.isRegisterBtnClick;
    },
    //注册功能
    register() {
      if(!this.newUser.username || !this.newUser.password || !this.newUser.confirmPwd){
        this.$message({
            message: "please fill in the blank",
            type: "error",
          });
        return
      }
      if(this.newUser.password.length<6){
        this.$message({
            message: "password length should greater than 6！",
            type: "error",
          });
        return
      }
      if(this.newUser.password!=this.newUser.confirmPwd){
        this.$message({
            message: "different password！",
            type: "error",
          });
        return
      }


      this.$axios.post("/User/register", this.newUser).then((res) => {
        if (res.data.code == 200) {
          //后端返回状态码200意为成功
          this.$message({
            message: "Register success!",
            type: "success",
          });
          this.registerBtn(); //调用方法转到登录面板
          //帮助用户填好登录信息
          this.user.username = this.newUser.username;
          this.user.password = this.newUser.password;
        } else {
          //注册失败
          this.$message.error("Register fail! Exist same username");
        }
      });
    },
  },
  // created() {
  //   console.log("登录页token值：", localStorage.getItem("token"));
  // },
};
</script>

<style scoped>
.login {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}
.index {
  width: 1000px;
  height: 500px;
  display: flex;
  justify-content: center;
}
.picture {
  width: calc(100% - 400px);
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.title {
  text-align: center;
  font-size: 36px;
  user-select: none;
  letter-spacing: 2px;
}
.title-small {
  text-align: center;
  font-size: 30px;
  user-select: none;
  letter-spacing: 2px;
  margin: 10px 0;
}
.picture img {
  height: 80%;
}
.menu {
  width: 400px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
/* 卡片 */
.el-card {
  width: 60px;
}
.text {
  font-size: 14px;
}
.item {
  margin-bottom: 18px;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}
.box-card {
  width: 100%;
}
/* ---结束卡片---- */
.option {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  font-size: small;
}
</style>