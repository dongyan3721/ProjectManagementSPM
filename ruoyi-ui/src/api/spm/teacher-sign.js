import request from "@/utils/request";

// 传参：只有courseId的SpmSign
function querySatisfiedPublishedSign(query){
  return request({
    url: '/spm/teacher-sign/query/sign',
    method: 'get',
    params: query
  })
}

// 传参：只有signId的SpmSignRecord
function querySatisfiedSignRecord(query){
  return request({
    url: '/spm/teacher-sign/query/sign-record',
    method: 'get',
    params: query
  })
}

// 查本课有哪些学生选了
function querySelfCourseStudentSelect(courseId){
  return request({
    url: '/spm/teacher-sign/select-student/'+courseId,
    method: 'get'
  })
}

// 某次签到详情
function queryDetailedSign(signId){
  return request({
    url: '/spm/teacher-sign/sign-detail/'+signId,
    method: 'get'
  })
}

// 某学生签到记录的详情
function queryDetailedSignRecord(signRecordId){
  return request({
    url: '/spm/teacher-sign/sign-record-detail/'+signRecordId,
    method: 'get'
  })
}

// 老师发布签到
function publishNewSign(data){
  return request({
    url: '/spm/teacher-sign/sign/add',
    method: 'post',
    data: data
  })
}

// 老师手动给学生签到
function pushNewStudentSignRecord(data){
  return request({
    url: '/spm/teacher-sign/sign-record/add',
    method: 'post',
    data: data
  })
}

// 老师删除非法签到学生
function deleteFakeSignRecord(ids){
  return request({
    url: '/spm/teacher-sign/sign-record/delete/'+ids,
    method: 'delete'
  })
}

export {querySatisfiedPublishedSign, querySatisfiedSignRecord, querySelfCourseStudentSelect,
  queryDetailedSignRecord, queryDetailedSign, pushNewStudentSignRecord, publishNewSign, deleteFakeSignRecord}
