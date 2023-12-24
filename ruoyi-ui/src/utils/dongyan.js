// 获取当前时间yyyy-MM-dd HH:mm:SS格式
export function getCurrentTimeYYYYmmDDHHmmSS(){
  let currentDate = new Date();

// 获取当前的年份
  let year = currentDate.getFullYear();

// 获取当前的月份（注意，月份是从 0 开始计数的，所以要加 1）
  let month = currentDate.getMonth() + 1;

// 获取当前的日期
  let day = currentDate.getDate();

// 获取当前的小时
  let hours = currentDate.getHours();

// 获取当前的分钟
  let minutes = currentDate.getMinutes();

// 获取当前的秒数
  let seconds = currentDate.getSeconds();

// 输出当前时间
  return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
}
