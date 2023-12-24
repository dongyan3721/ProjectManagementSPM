import request from "@/utils/request";

export function queryAllSatisfiedSign(query){
  return request({
    url: '/spm/student-sign/query/sign',
    method: 'get',
    params: query
  })
}

export function queryIfSigned(query){
  return request({
    url: '/spm/student-sign/query/sign-record',
    method: 'get',
    params: query
  })
}

export function addSignRecord(data){
  return request({
    url: '/spm/student-sign/add/sign-record',
    method: 'post',
    data: data
  })
}
