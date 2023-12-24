<template>
  <div class="item-wrapper" @click="handleIdCallback">
    <div class="item-line">
      <div class="title-avatar">
        <div class="sign-avatar" :style="deprecated?'background-color: #515A60FF;':'background-color: #5093C0FF;'">
          签到
        </div>
        <h3>普通签到</h3>
      </div>
      <div class="snapshot">
        起止时间：{{snapshot}}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SignListItem",
  props:{
    snapshot: String,
    id: String,
    deprecated: Boolean
  },
  data(){
    return{

    }
  },
  methods: {
    handleIdCallback(){
      this.$emit("idClick", [this.id, this.deprecated]);
    }
  }
}
</script>

<style scoped>
.item-wrapper{
  background-color: #f9f9f9;
  box-shadow: 0 0 2px 0 rgba(81, 90, 96, 0.7);
  border-radius: 5px;
  height: auto;
  cursor: pointer;
  margin: 10px 0;
}
.item-line{
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.sign-avatar{
  width: 50px;
  height: 50px;
  border-radius: 10px;
  margin: 5px;
  color: #f9f9f9;
  display: flex;
  align-items: center;
  justify-content: center;
}
.title-avatar h3{
  margin: 0 0 0 20px;
  font-weight: lighter;
  font-size: 18px;
}
.title-avatar{
  display: flex;
  align-items: center;
  justify-content: center;
}
.snapshot{
  margin: 0 10px 0 0;
}
</style>
