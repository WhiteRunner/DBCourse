<template>
  <div class="goods-detail">

    <el-page-header @back="goBack" content="Description" title="Back"> </el-page-header>

    <div class="index">
      <div class="imgBox">
        <img :src="good.pic" alt="" />
      </div>
      <div class="desc">
        <div class="gname">
          {{ good.gname }}
        </div>
        <div class="price">￥{{ good.price }}</div>
        <div style="padding: 14px">
            <img src="../assets/avatar.png" alt="" style="width: 40px;height: 40px;float:left;"/>
            <span style="float:left;margin:0 20px;color: #27ae60;font-size:large">{{good.username}}</span>
            <el-rate v-if="owner.rate==null" :value="ownerRate" disabled 
                      text-color="#ff9900" 
                      score-template="{value}"
                      style="margin-top:5px">
            </el-rate>
            <el-rate v-if="owner.rate!=null" :value="ownerRate" disabled 
                      show-score text-color="#ff9900" 
                      score-template="{value}"
                      style="margin-top:5px">
            </el-rate>
        </div>
        <div class="remark">
          {{ good.remark }}
        </div>
        <div class="purchase">
          <!-- <el-button type="warning" style="width: 120px" @click="collectBtn" v-show="isCollected == false">加入收藏 </el-button > -->
          <!-- <el-button type="warning"  style="width: 120px"  @click="collectBtn"  v-show="isCollected == true"  disabled>已收藏</el-button> -->
          <el-button type="primary" style="width: 120px" @click="purchaseBtn">Purchase</el-button>
        </div>
      </div>
    </div>



    <div class="message">
      <!-- <div class="send-message">
        <el-form :inline="true" :model="addMessage" label-width="80px">
          <el-form-item>
            <el-input placeholder="给卖家留言试试" v-model="addMessage.content"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="width: 120px" @click="messageBtn">发布留言</el-button>
          </el-form-item>
        </el-form>
      </div>  -->

      <div class="message-list" v-if="comments.length!=0">
        <div class="clearfix" v-if="isDataArrive"> 
          <span style="float:left">Comments towards owner </span>
          <span style="float:left;margin-left:10px;color: #27ae60;font-size:large;line-height:18px">{{owner.username}}</span>
        </div>
        <div class="list-item" v-for="item in comments" :key="item.id">
          <el-divider></el-divider>
          <div class="list-item-index">
            <div class="message-avatar content-item">
              <img src="../assets/avatar.png" alt="" />
            </div>
            <div class="message-username content-item">{{ item.username }}</div>
            <div >
              <el-rate v-model="item.rate" disabled 
              show-score text-color="#ff9900" 
              score-template="{value}">
              </el-rate>
            </div>
          </div>
          <div class="message-content content-item" style="margin-top:10px">
              {{ item.detail }}
          </div>
        </div>
      </div>

      <div class="pagination" v-if="isPaginationShow==true">
        <el-pagination background layout="prev, pager, next" :page-size="4"  :total="total"  @current-change="page">
        </el-pagination>
      </div>
    </div>

  </div>
</template>
<script>
export default {
  data() {
    return {
      isDataArrive: false, //留言信息是否到达（防止报错）
      isCollected: false, //用户是否收藏该商品
      total: 0,
      isPaginationShow: false,
      gid: 1,
      good: {
        id: 1,
        uid: 1,
        gname: "",
        price: 0,
        pic: "",
        status: 0,
        remark: "",
        cid: 1,
      },
      comments: [{
        id:1,
        uid: 1,
        toUid:null,
        username: "",
        toUsername:'',
        detail: "",
        rate:0
      }],
      addMessage: {
        userId: null,
        goodId: null,
        content: "",
      },
      collect: {
        userId: null,
        goodId: null,
      },
      owner:{}
    };
  },
  computed:{
    ownerRate(){
      // return Math.round(this.owner.rate*100)/100
      return Math.round(this.owner.rate*100)/100
    }
  },
  watch: {
    total() {
      //监视总数，当总数超过4时显示分页，否则不显示
      this.isPaginationShow = this.total > 4 ? true : false;
    },
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    //购买按钮
    purchaseBtn() {
      let order={
        ownerId:this.good.uid,
        buyerId:localStorage.getItem("userId"),
        goodId:this.good.id,
      }
      this.$confirm('Are you sure to purchase?', 'ConfirmMessage', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(()=>{
          // console.log(this.good,order)
            this.$axios.post("/Orders/makeOrder", order)
            .then((res) => {
              console.log("购买成功", res);
              if(res.data.code==200){
                  this.$bus.$emit('updateUserMoney',res.data.data)
                  this.$message({
                    message: "Purchase Success",
                    type: "success",
                  });
                  this.$router.go(-1)
              }else{
                  this.$message({
                  message: "Purchase Fail, Maybe Not Enough Money",
                  type: "error",
                });          
              }
            })
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: 'Cancel purchase'
          });          
        });
    },
    //留言按钮
    // messageBtn() {
    //   this.addMessage.userId = localStorage.getItem("userId");
    //   this.addMessage.goodId = this.good.gid;
    //   console.log("发布留言：", this.addMessage);
    //   this.$axios.post("/addMessage", this.addMessage).then((res) => {
    //     console.log("留言成功", res);
    //     this.$message({
    //       message: "留言成功",
    //       type: "success",
    //     });
    //     this.addMessage.content = "";
    //     //再次获取留言列表（刷新）
    //     this.$axios
    //       .get("/findGoodMessage/" + this.good.gid + "/1/6")
    //       .then((res) => {
    //         console.log("获取到了留言列表", res);
    //         this.comments = res.data.data;
    //         this.total = res.data.data.totalSize;
    //       });
    //   });
    // },

    //分页
    page(currentPage) {
      this.$axios
        .get("/Comment/getList/",{
          params:{
            toUid:this.good.uid,
            pageNo:currentPage,
            pageSize:4,
          }
        })
        .then((res) => {
          console.log("获取到了留言列表", res);
          this.comments = res.data.data;
          this.total = res.data.dataCount;
        });        
    },

    //收藏按钮
    // collectBtn() {
    //   this.collect.goodId = this.good.gid;
    //   this.collect.userId = localStorage.getItem("userId");
    //   console.log("发送收藏请求", this.collect);
    //   this.$axios.post("/addBookmark", this.collect).then((res) => {
    //     console.log(res.data);
    //     this.isCollected = true;
    //     this.$message({
    //       message: "已加入收藏",
    //       type: "success",
    //     });
    //   });
    // }
  },
  created() {
    console.log("query:", this.$route.query.id);
    this.id = this.$route.query.id;
    //获取商品详情
    this.$axios.get("/Goods/getById" ,{
        params:{            
          id:this.id
        }
      }).then((res) => {
      this.good = res.data.data;
      console.log("id", this.good.id);

      //获取留言列表
      this.$axios
        .get("/Comment/getList/",{
          params:{
            toUid:this.good.uid,
            pageNo:1,
            pageSize:4,
          }
        })
        .then((res) => {
          console.log("获取到了留言列表", res);
          this.comments = res.data.data;
          this.total = res.data.dataCount;
          this.isDataArrive = true;
        });

      //获取商品拥有者信息
      this.$axios
        .get("/User/getById/",{
          params:{
           id:this.good.uid,
          }
        })
        .then((res) => {
          console.log("获取到了用户信息", res.data.data);
          this.owner=res.data.data
        });

      // //判断是否收藏该商品
      // this.$axios
      //   .get(
      //     "/findBookmark/" +
      //       localStorage.getItem("userId") +
      //       "/" +
      //       this.good.gid
      //   )
      //   .then((res) => {
      //     console.log("是否收藏:", res.data);
      //     this.isCollected = res.data;
      //   });
    });
  },
};
</script>
<style scoped>
.el-input {
  width: 400px;
}
.goods-detail {
  margin: 0 auto;
  padding: 12px 12px;
  padding-bottom: 100px;
  width: 1200px;
  background-color: #fff;
}
/* 详情页index */
.index {
  width: 1000px;
  margin: 20px auto;
  display: flex;
}
.index .imgBox {
  width: 400px;
  height: 400px;
  overflow: hidden;
}
.index .imgBox img {
  width: 100%;
}
.index .desc {
  width: 500px;
  margin-left: 40px;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
}
.index .desc .price {
  color: #27ae60;
  font-size: 28px;
}
.index .desc .gname {
  font-size: 36px;
  font-family: "Yuanti SC", Youyuan, "You Yuan", 幼圆, "PingFang SC",
    "Microsoft Yahei", Helvetica, sans-serif;
}
.index .desc .purchase .el-button {
  margin: 0 20px;
}
/* 留言页 */
.message {
  width: 1000px;
  margin: 0 auto;
}
.message .send-message {
  width: 800px;
  margin: 0 auto;
}
.message .message-list {
  width: 800px;
  margin: 0 auto;
}
.list-item-index {
  display: flex;
  align-items: center;
}
.list-item-index .content-item {
  margin-right: 10px;
}
.list-item-index .message-avatar {
  width: 40px;
  height: 40px;
  border-radius: 20px;
  background-color: rgb(173, 173, 173);
  overflow: hidden;
}
.list-item-index .message-avatar img {
  width: 100%;
}
.list-item-index .message-username {
  font-weight: 600;
  font-size: small;
  color: #27ae60;
}
.list-item-index .message-time {
  font-size: smaller;
  color: #7a7a7a;
}
/* pagination */
.pagination {
  width: 100%;
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
.pagination
  >>> .el-pagination.is-background
  .el-pager
  li:not(.disabled).active {
  background-color: var(--primary-color);
  color: #ffffff;
}
.pagination >>> .el-pagination.is-background .el-pager li:not(.disabled) {
  /* background-color: var(--primary-color); */
  color: #424242;
}
.clearfix:after {
   content:""; 
   display: block; 
   clear:both; 
}
</style>