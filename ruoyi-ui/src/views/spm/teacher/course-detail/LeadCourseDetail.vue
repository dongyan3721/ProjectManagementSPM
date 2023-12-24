<script>
import {queryDetailedLeadCourseInfo} from "@/api/spm/teacher-leadcoursedetail";
import {querySatisfiedHomework, insertNewHomework} from "@/api/spm/teacher-homework";
import SingleHomework from "@/components/Spm/SingleHomework";
import {parseTime} from "@/utils/ruoyi";

export default {
  name: "LeadCourseDetail",
  components: {SingleHomework},
  data(){
    return {
      activeName: 'chapter',
      courseInfo: {},
      homeworkList: [],


      addDialogVis: false,
      form: {},
      formRule: {},
      fileContent: null,
      dynamicLoadHomeworkKey: 0,


      isFormVisible: false,
      formData: {
        field104: '',
        field102: '',
        field103: '',
      },
      field103fileList: [], // adjust the file list based on your needs
      field103Action: 'your-upload-api-endpoint', // replace with your actual API endpoint
      rules: {
        field104: [{required: true, message: '请输入章节名称', trigger: 'blur'}],
        field102: [{required: true, message: '请输入章节描述', trigger: 'blur'}],
      },


      activeCollapse: ['1'],
      isDialogVisible: false,
      pdfPath: '',
    };


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
    },




    showDialog() {
      this.isFormVisible = true;
    },
    close() {
      this.isFormVisible = false;
    },
    handleConfirm() {
      // handle form confirmation logic here
      // you can submit the form data or perform other actions
      this.isFormVisible = false;
    },
    field103BeforeUpload(file) {
      // handle file upload logic here
      // you can customize the upload behavior based on your requirements
      return true; // return false to prevent upload
    },

    openDialog(pdfFileName) {
      this.isDialogVisible = true;
      this.pdfPath = `/public/${pdfFileName}`;
    },
    closeDialog() {
      this.isDialogVisible = false;
    },
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
      <el-tab-pane label="章节完善" name="chapter">

        <el-button @click="showDialog" type="danger">添加章节</el-button>

        <el-dialog :visible="isFormVisible" @close="close" title="章节完善" width="50%">
          <el-form :model="formData" :rules="rules" ref="elForm" label-position="top">
            <el-form-item label="章节名称" prop="field104">
              <el-input v-model="formData.field104" placeholder="请输入章节名称"></el-input>
            </el-form-item>

            <el-form-item label="章节描述" prop="field102">
              <el-input
                v-model="formData.field102"
                type="textarea"
                placeholder="请输入章节描述"
                :rows="4"
              ></el-input>
            </el-form-item>

            <el-form-item label="上传" prop="field103">
              <el-upload
                ref="field103"
                :file-list="field103fileList"
                :action="field103Action"
                :before-upload="field103BeforeUpload"
                list-type="picture-card"
              >
                <i class="el-icon-plus"></i>
              </el-upload>
            </el-form-item>
          </el-form>

          <div slot="footer">
            <el-button @click="close">取消</el-button>
            <el-button type="primary" @click="handleConfirm">确定</el-button>
          </div>
        </el-dialog>



        <div class="chapter-list">
          <el-collapse>
            <!-- 第一章 - 绪论 -->
            <template>
              <div>
                <el-collapse v-model="activeCollapse">
                  <el-collapse-item title="第一章 - 绪论">
                    <ul>
                      <li><a @click="openDialog('xulun.pptx')" href="#">课件 查看文件</a></li>
                      <!-- 其他链接 -->
                    </ul>
                  </el-collapse-item>
                </el-collapse>

                <el-dialog :visible="isDialogVisible" @close="closeDialog" title="课件 PPT">
                  <iframe :src="pdfPath" style="width: 100%; height: 500px;"></iframe>
                </el-dialog>
              </div>
            </template>

            <!-- 第二章 - 项目初始 -->
            <el-collapse-item title="第二章 - 项目初始">
              <ul>
                <li><a :href="initialProject" target="_blank">第二章项目初始 查看文件</a></li>
                <li>
                  <a :href="exercisePersonal" target="_blank">
                    课后练习，个人完成
                    <span v-if="exerciseExpired">已过期</span>
                    <span v-if="exerciseSubmitted">已交</span>
                    <span>作业交付截止 {{ exerciseDeadline }}</span>
                  </a>
                </li>
              </ul>
            </el-collapse-item>

            <!-- 第三章 - 生存期模型 -->
            <el-collapse-item title="第三章 - 生存期模型">
              <ul>
                <li><a :href="lifeModel" target="_blank">生存期模型 查看文件</a></li>
              </ul>
            </el-collapse-item>

            <!-- 第四章 - 范围计划 -->
            <el-collapse-item title="第四章 - 范围计划">
              <ul>
                <li><a :href="scopePlanPpt" target="_blank">课件 查看文件</a></li>
                <li>
                  <a :href="projectWbs" target="_blank">
                    某个项目的WBS，个人提交
                    <span v-if="wbsExpired">已过期</span>
                    <span v-if="wbsSubmitted">已交</span>
                    <span>作业交付截止 {{ wbsDeadline }}</span>
                  </a>
                </li>
                <li>
                  <a :href="weeklyReport1" target="_blank">
                    第一次周报，以小组为单位提交
                    <span v-if="weeklyReport1Expired">已过期</span>
                    <span>作业交付截止 {{ weeklyReport1Deadline }}</span>
                  </a>
                </li>
              </ul>
            </el-collapse-item>

            <!-- 第六章 - 成本计划 -->
            <el-collapse-item title="第六章 - 成本计划">
              <ul>
                <li><a :href="costPlanPpt" target="_blank">成本计划 查看文件</a></li>
                <li>
                  <a :href="weeklyReport2" target="_blank">
                    第二次周报 内容 成本计划 以小组为单位提交
                    <span v-if="weeklyReport2Expired">已过期</span>
                    <span>作业交付截止 {{ weeklyReport2Deadline }}</span>
                  </a>
                </li>
              </ul>
            </el-collapse-item>

            <!-- 第七章 - 进度计划 -->
            <el-collapse-item title="第七章 - 进度计划">
              <ul>
                <li><a :href="schedulePlanPpt" target="_blank">课件 查看文件</a></li>
                <li>
                  <a :href="weeklyReport3" target="_blank">
                    第三周周报 进度计划 以小组为单位提交
                    <span v-if="weeklyReport3Expired">已过期</span>
                    <span>作业交付截止 {{ weeklyReport3Deadline }}</span>
                  </a>
                </li>
              </ul>
            </el-collapse-item>

            <!-- 第八章 - 质量计划 -->
            <el-collapse-item title="第八章 - 质量计划">
              <ul>
                <li><a :href="qualityPlanPpt" target="_blank">课件 查看文件</a></li>
              </ul>
            </el-collapse-item>

            <!-- 第九章 - 配置管理计划 -->
            <el-collapse-item title="第九章 - 配置管理计划">
              <ul>
                <li><a :href="configPlanPpt" target="_blank">课件 查看文件</a></li>
                <li>
                  <a :href="configItemRules" target="_blank">
                    关于配置项版本号规则
                    <span v-if="configRulesExpired">已过期</span>
                    <span v-if="configRulesSubmitted">已作答</span>
                    <span>测试截止时间 {{ configRulesDeadline }}</span>
                  </a>
                </li>
              </ul>
            </el-collapse-item>

            <!-- 第十一章 - 风险计划 -->
            <el-collapse-item title="第十一章 - 风险计划">
              <ul>
                <li><a :href="riskPlanPpt" target="_blank">课件 查看文件</a></li>
                <li>
                  <a :href="riskExercise" target="_blank">
                    课后习题第四大题第二小题
                    <span v-if="riskExerciseExpired">已过期</span>
                    <span v-if="riskExerciseSubmitted">已交</span>
                    <span>作业交付截止 {{ riskExerciseDeadline }}</span>
                  </a>
                </li>
              </ul>
            </el-collapse-item>

            <!-- 第十二、十三章 - 课件 -->
            <el-collapse-item title="第十二、十三章">
              <ul>
                <li><a :href="chapters1213Ppt" target="_blank">课件 查看文件</a></li>
              </ul>
            </el-collapse-item>

            <!-- 第十章 - 团队计划 -->
            <el-collapse-item title="第十章 - 团队计划">
              <ul>
                <li><a :href="teamPlanPpt" target="_blank">课件 查看文件</a></li>
              </ul>
            </el-collapse-item>

            <!-- 第十四章 - 课件 -->
            <el-collapse-item title="第十四章">
              <ul>
                <li><a :href="chapter14Ppt" target="_blank">课件 查看文件</a></li>
              </ul>
            </el-collapse-item>

            <!-- 第十五、十六章 - 课件 -->
            <el-collapse-item title="第十五、十六章">
              <ul>
                <li><a :href="chapters1516Ppt" target="_blank">课件 查看文件</a></li>
              </ul>
            </el-collapse-item>

            <!-- 最终章 - 报告要求 -->
            <el-collapse-item title="最终章 - 报告要求">
              <ul>
                <li><a :href="finalReportRequirements" target="_blank">报告要求 查看文件</a></li>
              </ul>
            </el-collapse-item>

          </el-collapse>
        </div>

      </el-tab-pane>
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

.el-upload__tip {
  line-height: 1.2;
}


</style>
