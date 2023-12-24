import request from '@/utils/request'

// 查询课程章节列表
export function listChapter(query) {
  return request({
    url: '/spm/chapter/list',
    method: 'get',
    params: query
  })
}

// 查询课程章节详细
export function getChapter(id) {
  return request({
    url: '/spm/chapter/' + id,
    method: 'get'
  })
}

// 新增课程章节
export function addChapter(data) {
  return request({
    url: '/spm/chapter',
    method: 'post',
    data: data
  })
}

// 修改课程章节
export function updateChapter(data) {
  return request({
    url: '/spm/chapter',
    method: 'put',
    data: data
  })
}

// 删除课程章节
export function delChapter(id) {
  return request({
    url: '/spm/chapter/' + id,
    method: 'delete'
  })
}
