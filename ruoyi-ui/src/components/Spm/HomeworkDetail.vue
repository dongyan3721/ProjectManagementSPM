<template>
  <div class="item-wrapper">
    <div class="homework-title">
      <h2>{{title}}</h2>
    </div>
    <div class="homework-content">
      <div class="snapshot">
        <p>{{snapshot}}</p>
      </div>
      <div class="appendix-download">
        <el-button type="text" icon="el-icon-download" @click="handleAppendixDownload">下载作业附件</el-button>
      </div>
    </div>
    <div class="content-show">
      <p>学习任务：</p>
      <textarea v-model="content"></textarea>
    </div>
  </div>
</template>

<script>
import {download, parseFileExtend} from "@/utils/request";

export default {
  name: "HomeworkDetail",
  props:{
    title: String,
    snapshot: String,
    downloadPath: String,
    content: String
  },
  methods:{
    handleAppendixDownload(){
      download(this.downloadPath, {}, `${this.title}.${parseFileExtend(this.downloadPath)}`);
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
.homework-content .snapshot{
  margin: 0 10px;
}
.content-show textarea{
  width: 90%;
  height: 90px;
  font-size: 16px;
  margin: 20px;
  resize: none;
}
.content-show p{
  margin: 0 20px;
}
</style>
