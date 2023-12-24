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

      activeCollapse: ['1'],
      isDialogVisible: false,
      pdfPath: '',

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
    this.requestThisCourseAllSign();
    document.body.style.cursor = "default"
  }
}
</script>

<template>
  <div class="container-wrapper">
    <el-tabs v-model="activeName" type="border-card" @tab-click="handleTabsClick" class="body-main-tabs">
      <el-tab-pane label="章节" name="chapter">


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
