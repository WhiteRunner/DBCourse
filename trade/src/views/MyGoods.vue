<template>
  <div class="my-goods">
    <el-page-header @back="goBack" title="Back"> </el-page-header>
    <el-tabs type="border-card">
      <!-- 1、我的发布模块 -->
      <el-tab-pane label="Goods Uploaded">
        <!-- 1.1、没有已发布商品的情况 -->
        <div class="no-goods" v-if="isHasGoodsForSell == false">
          <img src="../assets/no-goods.png" alt="no-goods" />
          <div>No Uploaded Goods</div>
        </div>
        <!-- 1.2、有已发布商品的情况 -->
        <div v-else>
          <div v-for="item in goods" :key="item.id">
            <div class="goods-item">
              <div class="img-box">
                <img :src="item.pic" alt="" />
              </div>
              <div class="desc">
                <div class="gname">
                  <span>{{ item.gname }}</span>
                  <el-divider direction="vertical"></el-divider>
                  <span class="price">￥{{ item.price }}</span>
                </div>
                <div class="remark">
                  <span>{{ item.remark | ellipsis_remark }}</span>
                  <el-divider direction="vertical"></el-divider>
                  <span>{{ item.status == 0 ? "Unsold" : "Sold" }}</span>
                  <!-- <span>{{ item.time | ellipsis_time }}</span> -->
                </div>
                <!-- <div class="status">
                  <span>等待发货</span>
                  <el-button type="primary">立即发货</el-button>
                </div> -->
              </div>
            </div>
            <el-divider></el-divider>
          </div>
          <!-- pagination（卖出模块） -->
          <div class="sell-pagination">
            <el-pagination
              background
              layout="prev, pager, next"
              :page-size="4"
              :total="goodsTotal"
              @current-change="page"
            >
            </el-pagination>
          </div>
        </div>
      </el-tab-pane>

      <!-- 2.我的卖出模块 -->
      <el-tab-pane label="Goods Sold">
        <!-- 1.1、没有已发布商品的情况 -->
        <div class="no-goods" v-if="isHasGoodsSold == false">
          <img src="../assets/no-goods.png" alt="no-goods" />
          <div>No Sold Goods</div>
        </div>
        <!-- 1.2、有已发布商品的情况 -->
        <div v-else>
          <div v-for="item in goodsSold" :key="item.id">
            <div class="goods-item">
              <div class="img-box">
                <img :src="item.pic" alt="" />
              </div>
              <div class="desc">
                <div class="gname">
                  <span>{{ item.gname }}</span>
                  <el-divider direction="vertical"></el-divider>
                  <span class="price">￥{{ item.price }}</span>
                </div>
                <div class="remark">
                  <span>{{ item.remark | ellipsis_remark }}</span>
                  <el-divider direction="vertical"></el-divider>
                  <span>{{ item.status == 0 ? "Unsold" : "Sold" }}</span>
                  <!-- <span>{{ item.time | ellipsis_time }}</span> -->
                </div>
                <!-- <div class="status">
                  <span>等待发货</span>
                  <el-button type="primary">立即发货</el-button>
                </div> -->
              </div>
            </div>
            <el-divider></el-divider>
          </div>
          <!-- pagination（卖出模块） -->
          <div class="sell-pagination">
            <el-pagination
              background
              layout="prev, pager, next"
              :page-size="4"
              :total="goodsSoldTotal"
              @current-change="pageSold"
            >
            </el-pagination>
          </div>
        </div>
      </el-tab-pane>

      <!-- 3.我的买到模块 -->
      <el-tab-pane label="Goods Purchased">
        <!-- 1.1、没有已发布商品的情况 -->
        <div class="no-goods" v-if="isHasGoodsPurchased == false">
          <img src="../assets/no-goods.png" alt="no-goods" />
          <div>No Purchased Goods</div>
        </div>
        <!-- 1.2、有已发布商品的情况 -->
        <div v-else>
          <div v-for="item in goodsPurchased" :key="item.id">
            <div class="goods-item">
              <div class="img-box">
                <img :src="item.pic" alt="" />
              </div>
              <div class="desc">
                <div class="gname">
                  <span>{{ item.gname }}</span>
                  <el-divider direction="vertical"></el-divider>
                  <span class="price">￥{{ item.price }}</span>
                </div>
                <div class="remark">
                  <span>{{ item.remark | ellipsis_remark }}</span>
                  <el-divider direction="vertical"></el-divider>
                  <span>Purchased</span>
                  <!-- <span>{{ item.time | ellipsis_time }}</span> -->
                </div>
                <el-button type="primary" @click="commentForm = true">Make Comment</el-button>
              </div>
            </div>
            <!-- 评论模块 -->
            <el-dialog title="Make Comments" :visible.sync="commentForm">
              <el-form :model="form">
                <el-form-item label="Towards" :label-width="formLabelWidth"> 
                   <span style="color: #27ae60;font-size:large;">{{item.username}}</span>
                </el-form-item>
                <el-form-item label="Rate" :label-width="formLabelWidth"> 
                  <el-rate v-model="form.rate" style="margin-top:10px"></el-rate>
                </el-form-item>
                <el-form-item label="Comment" :label-width="formLabelWidth">
                  <el-input v-model="form.detail" autocomplete="off"></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="commentForm = false">Cancel</el-button>
                <el-button type="primary" @click="makeComment(item.uid)">Submit</el-button>
              </div>
            </el-dialog>
            <el-divider></el-divider>
          </div>

          <!-- pagination -->
          <div class="sell-pagination">
            <el-pagination
              background
              layout="prev, pager, next"
              :page-size="4"
              :total="goodsPurchasedTotal"
              @current-change="pagePurchased"
            >
            </el-pagination>
          </div>
        </div>
      </el-tab-pane>

      <!-- 我的退款模块 -->
      <!-- <el-tab-pane label="我的退款">我的退款</el-tab-pane> -->
    </el-tabs>
  </div>
</template>
<script>
import qs from "qs";
export default {
  data() {
    return {
      //评论输入框
      commentForm:false,
      formLabelWidth: '120px',
      form:{
        detail:'',
        rate:0,
        uid:0,
        toUid:0
      },
      //发布产品
      goods: [],
      goodsTotal: 0,
      isHasGoodsForSell: false,
      //卖出产品
      goodsSold: [],
      goodsSoldTotal: 0,
      isHasGoodsSold: false,
      //买入产品
      goodsPurchased: [],
      goodsPurchasedTotal: 0,
      isHasGoodsPurchased: false,
      // goodIds:""
    };
  },
  watch: {
    //监听goods是否为空
    goods() {
      this.isHasGoodsForSell = this.goods.length == 0 ? false : true;
    },
    goodsSold() {
      this.isHasGoodsSold = this.goodsSold.length == 0 ? false : true;
    },
    goodsPurchased() {
      this.isHasGoodsPurchased = this.goodsPurchased.length == 0 ? false : true;
    },
  },
  filters: {
    ellipsis_remark(value) {
      //字数过滤器，用于限制商品详情的字数显示
      if (!value) return "";
      if (value.length > 50) {
        return value.slice(0, 50) + "...";
      }
      return value;
    },
    ellipsis_time(value) {
      //字数过滤器，用于规范化时间
      if (!value) return "";
      if (value.length > 10) {
        return value.slice(0, 10);
      }
      return value;
    },
  },
  methods: {
    makeComment(toUid){
      this.form.toUid=toUid
      this.form.detail=this.form.detail.replace(/^\s\s*/, '').replace(/\s\s*$/, '')
      console.log(this.form)
      if(this.form.detail=='' || this.form.rate==0){
        this.$message.error("Please input rate and comment!");
      }else{
        this.commentForm = false
        this.$axios
          .post("/Comment/insert",this.form)
          .then(res=> {
            if(res.data.code==200){
              this.$message({
                message: "make comment success",
                type: "success",
              });
            }
          })
      }
    },
    page(currentPage) {
      // console.log("第", currentPage, "页");
      this.$axios
        .get("/Goods/getList", {
          params: {
            uid: localStorage.getItem("userId"),
            pageNo: currentPage,
            pageSize: 4,
          },
        })
        .then((res) => {
          this.goods = res.data.data;
          this.goodsTotal = res.data.dataCount;
        });
    },
    pageSold(currentPage) {
      this.$axios
        .get("/Goods/getList", {
          params: {
            uid: localStorage.getItem("userId"),
            pageNo: currentPage,
            pageSize: 4,
            status: 1,
          },
        })
        .then((res) => {
          this.goodsSold = res.data.data;
          this.goodsSoldTotal = res.data.dataCount;
        });
    },
    pagePurchased(currentPage) {
      this.$axios
        .get("/Orders/getList", {
          params: {
            buyerId: localStorage.getItem("userId"),
            pageNo: currentPage,
            pageSize: 4,
          },
        })
        .then((res) => {
          // this.goodsPurchasedTotal=res.data.dataCount
          let ids = [];
          res.data.data.forEach((e) => {
            ids.push(e.goodId);
          });
          // this.goodIds=ids
          // console.log("-----------------",this.goodIds)
          this.$axios
            .get("/Goods/getPurchasedGood", {
              params: { ids: ids },
              paramsSerializer: (params) => {
                return qs.stringify(params, { indices: false });
              },
            })
            .then((res) => {
              this.goodsPurchased = res.data.data;
            });
        });
    },
    goBack() {
      // this.$router.push("/allGoods");
      this.$router.go(-1);
    },
  },
  created() {
    // this.$bus.$emit("hideSearch", true);
    this.form.uid = localStorage.getItem("userId");
    //上传的商品
    this.$axios
      .get("/Goods/getList", {
        params: {
          uid: localStorage.getItem("userId"),
          pageNo: 1,
          pageSize: 4,
        },
      })
      .then((res) => {
        this.goods = res.data.data;
        this.goodsTotal = res.data.dataCount;
      });
    //卖出去的商品
    this.$axios
      .get("/Goods/getList", {
        params: {
          uid: localStorage.getItem("userId"),
          pageNo: 1,
          pageSize: 4,
          status: 1,
        },
      })
      .then((res) => {
        this.goodsSold = res.data.data;
        this.goodsSoldTotal = res.data.dataCount;
      });

    //买入的商品
    this.$axios
      .get("/Orders/getList", {
        params: {
          buyerId: localStorage.getItem("userId"),
          pageNo: 1,
          pageSize: 4,
        },
      })
      .then((res) => {
        this.goodsPurchasedTotal = res.data.dataCount;
        let ids = [];
        res.data.data.forEach((e) => {
          ids.push(e.goodId);
        });
        // this.goodIds=ids
        // console.log("-----------------",this.goodIds)
        if (ids.length != 0) {
          this.$axios
            .get("/Goods/getPurchasedGood", {
              params: { ids: ids },
              paramsSerializer: (params) => {
                return qs.stringify(params, { indices: false });
              },
            })
            .then((res) => {
              this.goodsPurchased = res.data.data;
            });
        }
      });
  },
};
</script>
<style scoped>
.my-goods {
  margin: 0 auto;
  padding: 12px 12px;
  width: 1200px;
  background-color: #f1f1f1;
}
.goods-item {
  width: 900px;
  height: 200px;
  margin: 20px auto;
  display: flex;
  background-color: #fff;
  /* border-radius: 20px; */
  overflow: hidden;
}
.goods-item .img-box {
  overflow: hidden;
  width:250px;
  border-radius: 20px;
}
.goods-item .img-box img {
  height: 100%;
  width: 100%;
  object-fit: cover;
}
.goods-item .desc {
  width: 70%;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
}
.goods-item .desc .status {
  font-size: x-large;
  color: #d35400;
  width: 300px;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}
.goods-item .desc .gname {
  font-size: x-large;
  font-weight: 500;
}
.goods-item .desc .gname .price {
  color: #16a085;
}
.goods-item .desc .remark {
  color: rgb(90, 90, 90);
}
/* tab */
.el-tabs {
  margin: 10px auto;
  height: 1200px;
}
.my-goods >>> .is-active {
  color: var(--primary-color) !important;
  font-size: 16px;
  font-weight: 600;
}
.my-goods >>> .el-tabs__item:hover {
  color: var(--primary-color) !important;
  font-size: 16px;
  font-weight: 600;
}
/* 卖出模块 */
/* 没有商品时 */
.no-goods {
  width: 100%;
  height: 500px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.no-goods img {
  width: 300px;
}
/* pagination（卖出） */
.sell-pagination {
  width: 100%;
  height: 40px;
  margin: 10px 0;
  display: flex;
  justify-content: center;
}
.sell-pagination
  >>> .el-pagination.is-background
  .el-pager
  li:not(.disabled).active {
  background-color: var(--primary-color);
  color: #ffffff;
}
.sell-pagination >>> .el-pagination.is-background .el-pager li:not(.disabled) {
  color: #424242;
}
</style>
