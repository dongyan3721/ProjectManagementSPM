<script>
import {queryDetailedLeadCourseInfo} from "@/api/spm/teacher-leadcoursedetail";

export default {
  name: "LeadCourseDetail",
  data(){
    return{
      activeName: 'chapter',
      courseInfo: {}
    }
  },
  methods: {
    handleTabsClick(tab, ev){
      console.log(tab, ev);
    },
    selectDetailedCourseInformation(){
      queryDetailedLeadCourseInfo(this.$route.params.courseId).then(res=>{
        this.courseInfo = {...res.data};
        console.log(this.courseInfo)
      })
    }
  },
  created() {
    this.selectDetailedCourseInformation();
    document.body.style.cursor = "default"
  }
}
</script>

<template>
  <div class="container-wrapper">
    <el-tabs v-model="activeName" type="border-card" @tab-click="handleTabsClick" class="body-main-tabs">
      <el-tab-pane label="章节完善" name="chapter">用户管理</el-tab-pane>
      <el-tab-pane label="发布课程作业" name="homework">配置管理</el-tab-pane>
      <el-tab-pane label="点名" name="sign">角色管理</el-tab-pane>
      <el-tab-pane label="考试" name="exam">定时任务补偿</el-tab-pane>
      <el-tab-pane label="本班学生" name="classmates">本班学生</el-tab-pane>
      <!--当管理员启用教师评价功能后才显示评价功能-->
<!--      <el-tab-pane label="教师评价" name="judge" v-show="courseInfo.enableTeacherComment==='1'">教师评价</el-tab-pane>-->
    </el-tabs>
  </div>
</template>

<style scoped lang="scss">

</style>
