<template>
  <div class="container">
    <div class="top-homework-detail">
      <HomeworkDetail :snapshot="`${homeworkInfo.beginTime}-${homeworkInfo.endTime}`"
                    :title="homeworkInfo.title"
                    :download-path="homeworkInfo.appendixLink"
                    :content="homeworkInfo.content"/>
    </div>
    <div class="body-handed-on-student">
      <div class="body-title">
        <h2>已提交学生信息</h2>
      </div>
      <el-table :data="submitStudentInfo">
        <el-table-column align="center" prop="studentId" label="学生学号"/>
        <el-table-column align="center" prop="studentName" label="学生姓名"/>
        <el-table-column align="center" prop="submitTime" label="上传时间"/>
        <el-table-column label="操作" align="center">
          <template #default="scope">
            <el-button icon="el-icon-download" @click="handleAppendixDownload(scope.row)" size="small" type="text">下载学生作业</el-button>
            <el-button icon="el-icon-right" @click="handleStudentMarkRecordIn(scope.row)" size="small" type="text">登记作业成绩</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="bottom-none-submit-student">
      <div class="body-title">
        <h2>未提交学生信息</h2>
      </div>
      <el-table :data="baseCourseStudentInfo">
        <el-table-column label="学生学号" align="center" prop="id"/>
        <el-table-column label="学生姓名" align="center" prop="name"/>
        <el-table-column label="学生联系方式" align="center" prop="phone"/>
      </el-table>
    </div>
    <el-dialog :visible.sync="updateDialogVis" title="作业分数登记" append-to-body width="400px">
      <el-form :model="form" size="small" ref="form" :inline="true">
        <el-form-item label="作业得分">
          <el-input v-model="form.score" placeholder="请填写学生得分"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitUpdateForm" size="small">确认</el-button>
          <el-button type="danger" @click="cancelSubmit" size="small">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import {requestBaseCourseStudentInfoBackend, requestSubmitStudentInfoBackend, queryDetailHomework, addNewHomeScoreRecord} from "@/api/spm/teacher-homework";
import HomeworkDetail from "@/components/Spm/HomeworkDetail";
import {parseTime} from "@/utils/ruoyi";
import {download, parseFileExtend} from "@/utils/request";

export default {
  name: "TeacherHomework",
  components: {HomeworkDetail},
  data(){
    return {
      baseCourseStudentInfo: [],
      submitStudentInfo: [],
      homeworkInfo: {},
      courseId: this.$route.params.courseId,
      homeworkId: this.$route.params.homeworkIndex,
      form: {},
      updateDialogVis: false,
      isFirst: 0
    //   inList: (item, list)=>{
    //   for(let i=0;i<list.length;++i){
    //     if(item==list[i]){
    //       return true;
    //     }
    //   }
    //   return false;
    // }
    }
  },
  methods:{
    requestBaseCourseStudentInfo(){
      requestBaseCourseStudentInfoBackend(this.courseId).then(res=>{
        // console.log(res.rows)
        this.baseCourseStudentInfo = res.rows
      })
    },
    requestSubmitStudentInfo(){
      requestSubmitStudentInfoBackend({homeworkId: this.homeworkId}).then(res=>{
        // console.log(res.rows);
        this.submitStudentInfo = res.rows;
      })
    },
    requestHomeworkInfo(){
      queryDetailHomework(this.homeworkId).then(res=>{
        this.homeworkInfo = res.data
        this.homeworkInfo.beginTime = parseTime(this.homeworkInfo.beginTime)
        this.homeworkInfo.endTime = parseTime(this.homeworkInfo.endTime)
        // console.log(this.homeworkInfo)
      })
    },
    handleAppendixDownload(row){
      // console.log(row.contentLink)
      download(row.contentLink, {}, `${row.studentName}(${row.studentId})作业-${row.id}.${parseFileExtend(row.contentLink)}`)
    },
    handleStudentMarkRecordIn(row){
      this.resetForm("form");
      this.form.homeworkId = this.homeworkId;
      this.form.submitHomeworkId = row.id;
      this.form.studentId = row.studentId;
      this.form.studentName = row.studentName;
      this.updateDialogVis = true;
    },
    submitUpdateForm(){
      console.log(this.form)
      addNewHomeScoreRecord(this.form).then(res=>{
        this.$modal.msgSuccess("批改成功！")
        this.resetForm("form")
        this.updateDialogVis = false
      }).catch(err=>{
        console.log(err)
      })
    },
    cancelSubmit(){
      this.updateDialogVis = false;
      this.resetForm("form")
    }
  },
  created() {
    console.log("created")
    this.requestBaseCourseStudentInfo();
    this.requestSubmitStudentInfo();
    this.requestHomeworkInfo();
    this.form = {
      score: null,
      homeworkId: '',
      submitHomeworkId: '',
      studentId: '',
      studentName: ''
    }
  },
  mounted() {
    console.log("mounted")
    // this.$parent.$destroy();
    document.querySelector(".body-main-tabs").style.display = "none"
    // console.log(this.$route.params.courseId)
    // console.log(this.$route.params.homeworkIndex)
  },
  watch:{
    // 避免异步的特殊性导致清洗不成功，这里使用watch方案
    baseCourseStudentInfo(newValue, oldValue){
      if(this.submitStudentInfo.length!=0&&this.isFirst==0){
        console.log(this.baseCourseStudentInfo)
        console.log(this.submitStudentInfo)
        let ids = []
        for (let i = 0; i < this.submitStudentInfo.length; i++) {
          ids.push(this.submitStudentInfo[i].studentId);
        }
        // 这响应式数据不知道怎么搞的一定要这么写才能设置成功
        let s = this.baseCourseStudentInfo.map(function (element){
          function inList(item, list) {
            for(let i=0;i<list.length;++i){
              if(item==list[i]){
                return true;
              }
            }
            return false;
          }
          return inList(element.id, ids)?null:element
        }).filter(function (element){
          return element !== null;
        })
        this.baseCourseStudentInfo = s;
        ++this.isFirst
      }
    },
    submitStudentInfo(newValue, oldValue){
      if(this.baseCourseStudentInfo!=0&&this.isFirst==0){
        console.log(this.baseCourseStudentInfo)
        console.log(this.submitStudentInfo)
        let ids = []
        for (let i = 0; i < this.submitStudentInfo.length; i++) {
          ids.push(this.submitStudentInfo[i].studentId);
        }
        let s = this.baseCourseStudentInfo.map(function (element){
          function inList(item, list) {
            for(let i=0;i<list.length;++i){
              if(item==list[i]){
                return true;
              }
            }
            return false;
          }
          return inList(element.id, ids)?null:element
        }).filter(function (element){
          return element !== null;
        })
        this.baseCourseStudentInfo = s;
        ++this.isFirst;
      }
    }
  }
}
</script>

<style scoped>
.body-handed-on-student{
  margin: 10px;
  padding: 10px;
}
.body-title{
  display: flex;
  justify-content: center;
}
.body-title h2{
  font-family: 黑体;
  font-weight: bold;
}
</style>
