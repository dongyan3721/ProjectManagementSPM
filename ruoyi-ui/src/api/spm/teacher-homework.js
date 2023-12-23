import request from "@/utils/request";

export function querySatisfiedHomework(query){
  return request({
    url: 'spm/teacher-homework/query',
    method: 'get',
    params: query
  })
}

export function queryDetailHomework(id){
  return request({
    url: 'spm/teacher-homework/'+id,
    method: 'get'
  })
}

export function insertNewHomework(data){
  return request({
    url: 'spm/teacher-homework/add',
    method: 'post',
    data: data
  })
}

export function requestSubmitStudentInfoBackend(query){
  return request({
    url: 'spm/homework-submit/query',
    method: 'get',
    params: query
  })
}

export function requestBaseCourseStudentInfoBackend(courseId){
  return request({
    url: 'spm/teacher-homework/select-student/'+courseId,
    method: 'get'
  })
}

export function addNewHomeScoreRecord(data){
  return request({
    url: 'spm/homework-score/add',
    method: 'post',
    data: data
  })
}
