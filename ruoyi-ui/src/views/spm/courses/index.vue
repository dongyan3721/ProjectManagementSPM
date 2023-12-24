<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课程名" prop="courseName">
        <el-input
          v-model="queryParams.courseName"
          placeholder="请输入课程名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任课老师工号" prop="courseTeacherId">
        <el-input
          v-model="queryParams.courseTeacherId"
          placeholder="请输入任课老师工号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任课老师名字" prop="courseTeacherName">
        <el-input
          v-model="queryParams.courseTeacherName"
          placeholder="请输入任课老师名字"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['spm:courses:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['spm:courses:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['spm:courses:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['spm:courses:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="coursesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="课程号" align="center" prop="id" />
      <el-table-column label="课程名" align="center" prop="courseName" />
      <el-table-column label="任课老师工号" align="center" prop="courseTeacherId" />
      <el-table-column label="任课老师名字" align="center" prop="courseTeacherName" />
      <el-table-column label="是否启用学生评价" align="center" prop="enableTeacherComment"/>
      <el-table-column label="课程图片" align="center">
        <template slot-scope="scope">
          <el-image :src="'http://localhost:8080'+scope.row.courseCoverLink"></el-image>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['spm:courses:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['spm:courses:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-upload"
            @click="handleCourseAvatarModify(scope.row)"
            v-hasPermi="['spm:courses:edit']"
          >修改课程图片</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改课程对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程名" prop="courseName">
          <el-input v-model="form.courseName" placeholder="请输入课程名" />
        </el-form-item>
        <el-form-item label="任课老师工号" prop="courseTeacherId">
          <el-input v-model="form.courseTeacherId" placeholder="请输入任课老师工号" />
        </el-form-item>
        <el-form-item label="任课老师名字" prop="courseTeacherName">
          <el-input v-model="form.courseTeacherName" placeholder="请输入任课老师名字" />
        </el-form-item>
        <el-form-item label="请上传课程图片" v-if="title==='添加课程'">
          <el-upload
            action=""
            class="upload-demo"
            drag
            accept=".jpg"
            :auto-upload="false"
            :show-file-list="false"
            :on-change="handleCourseAvatarUpload"
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传.jpg文件</div>
          </el-upload>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="文件上传"
      :visible.sync="avatarModifyVis"
      width="400px"
      append-to-body>
      <el-upload
        action=""
        class="upload-demo"
        drag
        accept=".jpg"
        :auto-upload="false"
        :show-file-list="false"
        :on-change="submitCourseAvatarModify"
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">只能上传.jpg文件</div>
      </el-upload>
    </el-dialog>

  </div>
</template>

<script>
import { listCourses, getCourses, delCourses, addCourses, updateCourses, modifyCourseAvatar } from "@/api/spm/courses";

export default {
  name: "Courses",
  dicts: ["sys_yes_no"],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 课程表格数据
      coursesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        courseName: null,
        courseTeacherId: null,
        courseTeacherName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        courseName: [
          { required: true, message: "课程名不能为空", trigger: "blur" }
        ],
        courseTeacherId: [
          { required: true, message: "任课老师工号不能为空", trigger: "blur" }
        ],
        courseTeacherName: [
          { required: true, message: "任课老师名字不能为空", trigger: "blur" }
        ],
      },
      fileContent: null,
      avatarModifyVis: false,
      selectedRowId: null
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询课程列表 */
    getList() {
      this.loading = true;
      listCourses(this.queryParams).then(response => {
        this.coursesList = response.rows;
        this.coursesList.map(item=>{
          item.enableTeacherComment==='1'?item.enableTeacherComment='是':item.enableTeacherComment='否';
        })
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        courseName: null,
        courseCoverLink: null,
        courseTeacherId: null,
        courseTeacherName: null,
        enableTeacherComment: null,
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加课程";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCourses(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改课程";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCourses(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            let formData = new FormData();
            formData.append("courseContent", JSON.stringify(this.form));
            console.log(JSON.stringify(this.form))
            formData.append("courseAvatar", this.fileContent);
            addCourses(formData).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除课程编号为"' + ids + '"的数据项？').then(function() {
        return delCourses(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('spm/courses/export', {
        ...this.queryParams
      }, `courses_${new Date().getTime()}.xlsx`)
    },
    handleCourseAvatarUpload(ev){
      let file = ev.raw;
      if (!file) {
        this.$modal.msgError("文件打开失败！");
        return;
      }
      this.fileContent = file;
    },
    handleCourseAvatarModify(row){
      this.avatarModifyVis = true;
      this.selectedRowId = row.id
    },
    submitCourseAvatarModify(ev){
      let formData = new FormData()
      formData.append("fileContent", ev.raw);
      formData.append("courseId", this.selectedRowId);
      modifyCourseAvatar(formData).then(res=>{
        this.$modal.msgSuccess("修改成功！");
        this.avatarModifyVis = false;
        this.loading = true;
        this.getList();
        this.selectedRowId = null;
      })
    }
  }
};
</script>


