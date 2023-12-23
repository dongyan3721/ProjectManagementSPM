<script>
import {queryDetailedLeadCourseInfo} from "@/api/spm/teacher-leadcoursedetail";
import {querySatisfiedHomework, insertNewHomework} from "@/api/spm/teacher-homework";
import SingleHomework from "@/components/Spm/SingleHomework";
import {parseTime} from "@/utils/ruoyi";

export default {
  name: "LeadCourseDetail",
  components: {SingleHomework},
  data(){
    return{
      activeName: 'chapter',
      courseInfo: {},
      homeworkList: [],
      addDialogVis: false,
      form: {

      },
      formRule: {

      },
      fileContent: null,
      dynamicLoadHomeworkKey: 0
    }
  },
  methods: {
    handleTabsClick(tab, ev){
      console.log(tab, ev);
    },
    selectDetailedCourseInformation(){
      queryDetailedLeadCourseInfo(this.$route.params.courseId).then(res=>{
        this.courseInfo = {...res.data};
        // console.log(this.courseInfo)
      })
    },
    selectPublishedHomework(){
      querySatisfiedHomework({courseId: this.$route.params.courseId}).then(res=>{
        this.homeworkList = res.rows;
        this.homeworkList.map(item=>{
          item.beginTime = parseTime(item.beginTime);
          item.endTime = parseTime(item.endTime);
        })
      })
    },
    handleViewDetailHomeworkSubmit(id){
      this.$router.push(`/spm/teacher-detail/index/${this.$route.params.courseId}/homeworkIndex/${id}`)
      this.$destroy();
    },
    handleAddHomework(){
      this.resetForm("form");
      this.addDialogVis = true;
    },
    handleHomeworkAppendixChange(ev){
      this.fileContent = ev.raw
      this.$modal.msgSuccess("加载文件成功！")
    },
    submitAdd(){
      let formData = new FormData()
      this.form.courseId = this.$route.params.courseId
      formData.append("homeworkBody", JSON.stringify(this.form));
      formData.append("homeworkAppendix", this.fileContent)
      insertNewHomework(formData).then(res=>{
        this.$modal.msgSuccess("新增成功！");
        ++this.dynamicLoadHomeworkKey;
        this.addDialogVis = false;
      }).catch(err=>console.log(err))
    },
    cancelSubmit(){
      this.resetForm("form");
      this.addDialogVis = false;
    }
  },
  created() {
    this.selectDetailedCourseInformation();
    this.selectPublishedHomework();
    document.body.style.cursor = "default";
    this.form = {
      courseId: null,
      title: null,
      content: null,
      beginTime: null,
      endTime: null
    }
  },
  beforeRouteLeave(to, form, next){
    console.log("==========")
    this.$destroy();
    // document.querySelector("html").innerHTML = ""

    next();
  },
  destroyed() {
    console.log("des")
  }
}
</script>

<template>
  <div class="app-container">
    <el-dialog :visible.sync="addDialogVis" title="发布新作业" append-to-body width="400px">
      <el-form :rules="formRule" :model="form" size="small" :inline="true" ref="form">
        <el-form-item label="作业标题" prop="title">
          <el-input placeholder="请输入作业标题" v-model="form.title"/>
        </el-form-item>
        <el-form-item label="作业内容" prop="content">
          <el-input type="textarea" placeholder="请输入作业内容" v-model="form.content"/>
        </el-form-item>
        <el-form-item label="作业附件">
          <el-upload
            action=""
            class="upload-demo"
            drag
            accept=".doc, .docx"
            :auto-upload="false"
            :show-file-list="false"
            :on-change="handleHomeworkAppendixChange">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传.doc或.docx文件</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="作业开始时间" prop="beginTime">
          <el-date-picker
            v-model="form.beginTime"
            type="datetime"
            placeholder="请选择作业开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="作业结束时间" prop="endTime">
          <el-date-picker
            v-model="form.endTime"
            type="datetime"
            placeholder="请选择作业结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="small" @click="submitAdd">确认</el-button>
          <el-button type="danger" size="small" @click="cancelSubmit">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-tabs v-model="activeName" type="border-card" @tab-click="handleTabsClick" class="body-main-tabs">
      <el-tab-pane label="章节完善" name="chapter"></el-tab-pane>
      <el-tab-pane label="发布课程作业" name="homework">
        <el-row>
          <el-col :span="3">
            <el-button type="danger" size="medium" @click="handleAddHomework">布置新作业</el-button>
          </el-col>
        </el-row>
        <div class="body-exist-homework">
          <SingleHomework v-for="homework in homeworkList"
                          @idClick="handleViewDetailHomeworkSubmit"
                          :id="homework.id" :title="homework.title"
                          :snapshot="`${homework.beginTime}-${homework.endTime}`"
                          :all="7" :hand-on="4" :key="homework.id"></SingleHomework>
        </div>
      </el-tab-pane>
      <el-tab-pane label="点名" name="sign">角色管理</el-tab-pane>
      <el-tab-pane label="考试" name="exam">定时任务补偿</el-tab-pane>
      <el-tab-pane label="本班学生" name="classmates">
        <div class="whole">
          <el-table>
          </el-table>
        </div>
      </el-tab-pane>
      <!--当管理员启用教师评价功能后才显示评价功能-->
<!--      <el-tab-pane label="教师评价" name="judge" v-show="courseInfo.enableTeacherComment==='1'">教师评价</el-tab-pane>-->
    </el-tabs>
    <router-view/>
  </div>
</template>

<style scoped lang="scss">
.body-exist-homework{
  padding: 20px 0  0 0 ;
  height: auto;
}
::v-deep .body-main-tabs{
  height: auto;
}

</style>
