(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4b93fc2d"],{1195:function(t,e,a){t.exports=a.p+"img/avatar.be86d7b3.png"},1208:function(t,e,a){},"5ebe":function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"goods-detail"},[s("el-page-header",{attrs:{content:"Description",title:"Back"},on:{back:t.goBack}}),s("div",{staticClass:"index"},[s("div",{staticClass:"imgBox"},[s("img",{attrs:{src:t.good.pic,alt:""}})]),s("div",{staticClass:"desc"},[s("div",{staticClass:"gname"},[t._v(" "+t._s(t.good.gname)+" ")]),s("div",{staticClass:"price"},[t._v("￥"+t._s(t.good.price))]),s("div",{staticStyle:{padding:"14px"}},[s("img",{staticStyle:{width:"40px",height:"40px",float:"left"},attrs:{src:a("1195"),alt:""}}),s("span",{staticStyle:{float:"left",margin:"0 20px",color:"#27ae60","font-size":"large"}},[t._v(t._s(t.good.username))]),null==t.owner.rate?s("el-rate",{staticStyle:{"margin-top":"5px"},attrs:{value:t.ownerRate,disabled:"","text-color":"#ff9900","score-template":"{value}"}}):t._e(),null!=t.owner.rate?s("el-rate",{staticStyle:{"margin-top":"5px"},attrs:{value:t.ownerRate,disabled:"","show-score":"","text-color":"#ff9900","score-template":"{value}"}}):t._e()],1),s("div",{staticClass:"remark"},[t._v(" "+t._s(t.good.remark)+" ")]),s("div",{staticClass:"purchase"},[s("el-button",{staticStyle:{width:"120px"},attrs:{type:"primary"},on:{click:t.purchaseBtn}},[t._v("Purchase")])],1)])]),s("div",{staticClass:"message"},[0!=t.comments.length?s("div",{staticClass:"message-list"},[t.isDataArrive?s("div",{staticClass:"clearfix"},[s("span",{staticStyle:{float:"left"}},[t._v("Comments towards owner ")]),s("span",{staticStyle:{float:"left","margin-left":"10px",color:"#27ae60","font-size":"large","line-height":"18px"}},[t._v(t._s(t.owner.username))])]):t._e(),t._l(t.comments,(function(e){return s("div",{key:e.id,staticClass:"list-item"},[s("el-divider"),s("div",{staticClass:"list-item-index"},[t._m(0,!0),s("div",{staticClass:"message-username content-item"},[t._v(t._s(e.username))]),s("div",[s("el-rate",{attrs:{disabled:"","show-score":"","text-color":"#ff9900","score-template":"{value}"},model:{value:e.rate,callback:function(a){t.$set(e,"rate",a)},expression:"item.rate"}})],1)]),s("div",{staticClass:"message-content content-item",staticStyle:{"margin-top":"10px"}},[t._v(" "+t._s(e.detail)+" ")])],1)}))],2):t._e(),1==t.isPaginationShow?s("div",{staticClass:"pagination"},[s("el-pagination",{attrs:{background:"",layout:"prev, pager, next","page-size":4,total:t.total},on:{"current-change":t.page}})],1):t._e()])],1)},i=[function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"message-avatar content-item"},[s("img",{attrs:{src:a("1195"),alt:""}})])}],o={data:function(){return{isDataArrive:!1,isCollected:!1,total:0,isPaginationShow:!1,gid:1,good:{id:1,uid:1,gname:"",price:0,pic:"",status:0,remark:"",cid:1},comments:[{id:1,uid:1,toUid:null,username:"",toUsername:"",detail:"",rate:0}],addMessage:{userId:null,goodId:null,content:""},collect:{userId:null,goodId:null},owner:{}}},computed:{ownerRate:function(){return Math.round(100*this.owner.rate)/100}},watch:{total:function(){this.isPaginationShow=this.total>4}},methods:{goBack:function(){this.$router.go(-1)},purchaseBtn:function(){var t=this,e={ownerId:this.good.uid,buyerId:localStorage.getItem("userId"),goodId:this.good.id};this.$confirm("Are you sure to purchase?","ConfirmMessage",{confirmButtonText:"Confirm",cancelButtonText:"Cancel",type:"warning"}).then((function(){t.$axios.post("/Orders/makeOrder",e).then((function(e){200==e.data.code?(t.$bus.$emit("updateUserMoney",e.data.data),t.$message({message:"Purchase Success",type:"success"}),t.$router.go(-1)):t.$message({message:"Purchase Fail, Maybe Not Enough Money",type:"error"})}))})).catch((function(){t.$message({type:"info",message:"Cancel purchase"})}))},page:function(t){var e=this;this.$axios.get("/Comment/getList/",{params:{toUid:this.good.uid,pageNo:t,pageSize:4}}).then((function(t){e.comments=t.data.data,e.total=t.data.dataCount}))}},created:function(){var t=this;this.id=this.$route.query.id,this.$axios.get("/Goods/getById",{params:{id:this.id}}).then((function(e){t.good=e.data.data,t.$axios.get("/Comment/getList/",{params:{toUid:t.good.uid,pageNo:1,pageSize:4}}).then((function(e){t.comments=e.data.data,t.total=e.data.dataCount,t.isDataArrive=!0})),t.$axios.get("/User/getById/",{params:{id:t.good.uid}}).then((function(e){t.owner=e.data.data}))}))}},n=o,r=(a("99b1"),a("2877")),c=Object(r["a"])(n,s,i,!1,null,"7d21e6c7",null);e["default"]=c.exports},"99b1":function(t,e,a){"use strict";a("1208")}}]);
//# sourceMappingURL=chunk-4b93fc2d.ddfd80ae.js.map