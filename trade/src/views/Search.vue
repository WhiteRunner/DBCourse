<template>
  <div class="search">
    <el-page-header @back="goBack" content="Search Goods"> </el-page-header>
    <div class="search-result">
      <el-row>
        <el-col :span="8" v-for="item in goods">
          <el-card :body-style="{ padding: '0px' }">
            <div class="img-box">
              <img :src="item.pic" class="image" />
            </div>
            <div style="padding: 14px">
              <span>{{ item.gname }}</span>
            </div>
            <div class="remark">
              {{item.remark | ellipsis_remark}}
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      goods: [],
      input: "",
    };
  },
  filters: {
    ellipsis_remark(value) {
      //字数过滤器，用于限制商品详情的字数显示
      if (!value) return "";
      if (value.length > 25) {
        return value.slice(0, 25) + "...";
      }
      return value;
    },
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    search(input) {
      this.$axios
        .get("/Goods/getList",{
          params:{
            gname:input,
            pageNo:1,
            pageSize:9
          }
        })
        .then((res) => {
          console.log(res)
          this.goods = res.data.data;
          console.log("搜索结果：", this.goods);
        });
    },
  },
  watch: {
    $route() {
      let input = this.$route.query.input;
      this.search(input);
    },
  },
  created() {
    let input = this.$route.query.input;
    this.search(input);
  },
};
</script>
<style scoped>
.search {
  margin: 0 auto;
  padding: 12px 12px;
  padding-bottom: 100px;
  background-color: #fff;
}
.el-col {
  display: flex;
  justify-content: center;
  margin: 20px 0;
}
.el-card {
  width: 280px;
}
.img-box {
  width: 100%;
  height: 260px;
  overflow: hidden;
}
.img-box img {
  width: 100%;
}

</style>