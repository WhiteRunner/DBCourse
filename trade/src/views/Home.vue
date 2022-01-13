<template>
  <div class="home">
    <el-container>
      <el-header>
        <div class="logo">Second-hand Trade Platform</div>
        <div class="search">
          <el-input v-if="hideSearch==false"
            prefix-icon="el-icon-search"
            placeholder="Search Goods"
            v-model="input"
            @keyup.enter.native="search"
          ></el-input>
        </div>
        <div class="user-info">
          <!-- 添加按钮和收藏夹 -->
          <div class="bookmark">
            <el-button
              type="primary"
              icon="el-icon-plus"
              circle
              @click="addGoodsBtn()"
            ></el-button>
            <el-button
              type="warning"
              icon="el-icon-truck"
              circle
              @click="myGoodsBtn()"
            ></el-button>
            <!-- <el-button
              type="warning"
              icon="el-icon-star-off"
              circle
              @click="bookmarkBtn()"
            ></el-button> -->
          </div>
          <el-dropdown @command="handleCommand">
            <div class="avatar">
              <!-- <img v-if="!isLogin" src="../assets/avatar.png" alt="" />
              <img v-else :src="user.avatar" alt="" /> -->
              <img src="../assets/avatar.png" alt="" />
            </div>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item >account: {{ user.username }}</el-dropdown-item>
              <el-dropdown-item >balance: ￥{{ user.money }}</el-dropdown-item>
              <!-- <el-dropdown-item command="toCenter">更多信息</el-dropdown-item> -->
              <el-dropdown-item command="logout" divided>logout</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <!-- 路由嵌套 -->
      <router-view />
      <el-footer height="200px">
        <div>Copyright © 2022 Second-hand Trade Platform</div>
        <!-- <div class="link">
          <div @click="toAdmin">管理员页面</div>
          &nbsp;&nbsp;|&nbsp;&nbsp;
          <div>官方微博</div>
        </div>
        <div style="font-size: 12px">
          通信地址：广东省湛江市赤坎区岭南师范学院综B501
        </div> -->
      </el-footer>
    </el-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      isLogin: false,
      user: {
        id: null,
        username: "unknown",
        avatar: "../assets/avatar.png",
        money: null,
      },
      input: "",
      hideSearch:false
    };
  },
  methods: {
    //处理下拉菜单的方法
    handleCommand(command) {
      if (command == "toCenter") {
        //“更多信息”点击事件：跳转到个人中心页
        this.$router.push("/center");
      }
      if (command == "logout") {
        this.$store.commit("setToken", ""); //token设为空字符串
        this.$router.push("/");
        this.$message({
          message: "logout",
          type: "success",
        });
      }
    },
    //搜索
    search() {
      console.log("input",this.input);
      // this.$router.replace({
      //       // path: "/search",
      //       path: "/allGoods",
      //       query: {
      //         input: this.input,
      //     },
      //   });
      //防止用户两次搜索同一内容,路由提示不能navigate的问题
      this.$router.replace("/allGoods/JK91*-!2@sd")
      if(this.input!=''){
          this.$router.replace({
            // path: "/search",
            path: "/allGoods",
            query: {
              input: this.input,
          },
        });
      }else{
        this.$router.replace("/allGoods")
      }
      
    },
    //添加商品按钮
    addGoodsBtn() {
      this.$router.push("/addGoods");
    },
    myGoodsBtn() {
      this.$router.push("/myGoods");
    },
    //我的收藏按钮
    bookmarkBtn() {
      this.$router.push("/bookmark");
    },
    //----footer-----
    //跳转管理员页面
    toAdmin() {
      let superuser = this.$store.getters.getSuperuser;
      if (superuser == 1) {
        this.$router.push("/admin");
        this.$message({
          type: "success",
          message: "欢迎来到管理员页面",
        });
      } else {
        this.$message.error("请使用管理员账号登录");
      }
    }
  },
  created() {
    this.user.id = localStorage.getItem("userId");
    this.$axios.get("/User/findUserInfo/" + this.user.id).then((res) => {
      this.user.id = res.data.data.id;
      this.user.username = res.data.data.username;
      this.user.money = res.data.data.money;
      // this.user.avatar = "../assets/avatar.png";
      this.isLogin = true;
      console.log("this.user", this.user);
      console.log("isLogin", this.isLogin);
    });
  },
  mounted() {
    this.$bus.$on('changeInput',val=>{
      this.input=val
    })

    this.$bus.$on('hideSearch',val=>{
      this.hideSearch=val
    })

    this.$bus.$on('updateUserMoney',money=>{
      this.user.money=money
    })
  },
};
</script>
<style scoped>
.el-input >>> .el-input__inner {
  /* background-color: rgba(255, 255, 255, 0.4); */
  border: 0;
}
el-input {
  outline: none;
}
.el-header {
  padding: 0;
  display: flex;
  background-color: var(--primary-color);
}
.el-header .logo {
  flex: 1;
  color: #fff;
  line-height: 60px;
  font-size: 24px;
  letter-spacing: 1px;
  user-select: none;
  margin-left: 10px;
  font-family: "Yuanti SC", Youyuan, "You Yuan", 幼圆, "PingFang SC",
    "Microsoft Yahei", Helvetica, sans-serif;
}
.el-header .search {
  flex: 1;
  display: flex;
  align-items: center;
}
.el-header .user-info {
  flex: 1;
  display: flex;
  justify-content: flex-end;
}
.el-header .user-info .bookmark {
  /* width: 180px; */
  width: 120px;
  line-height: 60px;
  text-align: center;
  color: #fff;
  font-weight: 500;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
}
.el-header .user-info .avatar {
  margin: 10px 24px 10px 0;
  width: 40px;
  height: 40px;
  border-radius: 20px;
  background-color: rgb(185, 185, 185);
  cursor: pointer;
  overflow: hidden;
}
.el-header .user-info .avatar img {
  width: 100%;
}
.el-footer {
  background-color: var(--primary-color);
  color: #fff;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  line-height: 200%;
}
.link {
  display: flex;
  justify-content: center;
  font-size: small;
}
.link div {
  cursor: pointer;
}
.link div:hover {
  color: #f1c40f;
}
</style>