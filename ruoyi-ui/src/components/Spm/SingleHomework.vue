<template>
  <div class="item-wrapper" @click="handleIdCallback">
    <div class="homework-title">
      <h2>{{title}}</h2>
    </div>
    <div class="homework-content">
      <div class="snapshot">
        <p>{{snapshot}}</p>
      </div>
      <div class="hand-on-none-hand-on">
        <p>已交付：{{handOn}}份&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;班级总人数：{{all}}人</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SingleHomework",
  props:{
    title: String,
    snapshot: String,
    handOn: Number,
    all: Number,
    id: String
  },
  methods: {
    handleIdCallback(){
      this.$emit("idClick", this.id);
    }
  }
}
</script>

<style scoped>
.item-wrapper{
  background-color: #f9f9f9;
  box-shadow: 0 0 2px 0 rgba(81, 90, 96, 0.7);
  border-radius: 20px;
  height: auto;
  cursor: pointer;
  margin: 10px 0;
}
.homework-title {
  display: flex;
  align-items: center;
  justify-content: center;
}
.homework-title h2{
  font-weight: bold;
  font-family: 黑体;
}
.homework-content{
  display: flex;
  justify-content: space-between;
  margin: 0 10px;
}
</style>
