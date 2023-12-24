<script>
import {getDetailCourses} from "@/api/spm/student-mycoursedetail";
import SignListItemForStudent from "@/components/Spm/SignListItemForStudent";
import {queryAllSatisfiedSign, queryIfSigned, addSignRecord} from "@/api/spm/student-sign";
import {getCurrentTimeYYYYmmDDHHmmSS} from "@/utils/dongyan";
export default {
  name: "MyCourseDetail",
  components:{SignListItemForStudent},
  data(){
    return {
      activeName: 'chapter',
      // 页面初始化的时候会向后端获取本课程的所有信息
      courseInfo: {},
      publishedSignAvailable: [],
      publishedSignDeprecated: [],
    }
  },
  methods: {
    handleTabsClick(tab, ev){
      console.log(tab, ev);
    },
    selectDetailedCourseInformation(){
      getDetailCourses(this.$route.params.courseId).then(res=>{
        this.courseInfo = {...res.data};
        console.log(this.courseInfo)
      })
    },
    gotoSignDetail(args){
      queryIfSigned({signId: args[0]}).then(res=>{
        if(res.rows.length>0){
          this.$modal.msgSuccess("您已签到！")
        }else{
          if(args[1]){
            this.$modal.msgWarning("本课程您未签到！")
          }else{
            this.$modal.confirm("是否进行签到？").then(()=>{
              addSignRecord({signId: args[0], signTime: getCurrentTimeYYYYmmDDHHmmSS()}).then(res=>{
                this.$modal.msgSuccess("签到成功！")
                this.requestThisCourseAllSign();
              })
            })
          }
        }
      })
    },
    requestThisCourseAllSign(){
      queryAllSatisfiedSign({courseId: this.$route.params.coursrId}).then(res=>{
        let publishedSign = res.rows
        let now = getCurrentTimeYYYYmmDDHHmmSS();
        this.publishedSignAvailable = []
        this.publishedSignDeprecated = []
        publishedSign.map(item=>{
          item.endTime<now?this.publishedSignDeprecated.push(item):this.publishedSignAvailable.push(item)
        })
      })
    }
  },
  created() {
    this.selectDetailedCourseInformation();
    this.requestThisCourseAllSign();
    document.body.style.cursor = "default"
  }
}
</script>

<template>
  <div class="container-wrapper">
    <el-tabs v-model="activeName" type="border-card" @tab-click="handleTabsClick" class="body-main-tabs">
      <el-tab-pane label="章节" name="chapter">用户管理</el-tab-pane>
      <el-tab-pane label="作业" name="homework">配置管理</el-tab-pane>
      <el-tab-pane label="点名" name="sign">
        <div class="on-doing-task">
          <h2>进行中</h2>
          <div class="sign-info">
            <SignListItemForStudent v-for="item in publishedSignAvailable"
                          :id = item.id
                          :snapshot="`${item.beginTime}-${item.endTime}`"
                          :deprecated="false"
                          :key="item.id"
                          @idClick="gotoSignDetail"/>
          </div>
        </div>
        <div class="deprecated">
          <h2>已结束</h2>
          <div class="sign-info">
            <SignListItemForStudent v-for="item in publishedSignDeprecated"
                          :id = item.id
                          :snapshot="`${item.beginTime}-${item.endTime}`"
                          :deprecated="true"
                          :key="item.id"
                          @idClick="gotoSignDetail"/>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="考试" name="exam">定时任务补偿</el-tab-pane>
      <!--当管理员启用教师评价功能后才显示评价功能-->
      <el-tab-pane label="教师评价" name="judge" v-show="courseInfo.enableTeacherComment==='1'">教师评价</el-tab-pane>
    </el-tabs>
  </div>
</template>

<style scoped lang="scss">
.body-main-tabs{
  padding: 10px 0 0 10px;
}
</style>
