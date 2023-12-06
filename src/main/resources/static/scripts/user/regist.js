const idReg = /^[a-z0-9]{3,20}$/i;
const pwReg = /^(?=.*[a-zAz])(?=.*[!@#$%^&])(?=.*[0-9]).{10,30}$/;
const koreanReg = /[ㄱ-ㅎㅏ-ㅣ가-힣]/g;
const phoneReg = /^[0-9]{3}-[0-9]{3,4}-[0-9]{4}$/;

document.getElementById("registrationForm").onsubmit = function (e) {
  const tempName = e.target.name.value.replace(koreanReg, "aa");
  const tempPhone = e.target.phone.value.replace(
    /^(\d{3})(\d{3,4})(\d{4})$/,
    `$1-$2-$3`
  );

  let msg = "";

  if (!idReg.test(e.target.userId.value)) {
    if (e.target.userId.value.length < 3 || e.target.userId.value.length > 20) {
      msg = "아이디의 길이는 3~20으로 해주세요.";
    } else {
      msg = "아이디는 영어와 숫자만 포함할 수 있어요.";
    }
  } else if (!pwReg.test(e.target.password.value)) {
    if (e.target.password.value.length < 10 || e.target.password.value.length > 30) {
      msg = "비밀번호의 길이는 10~30으로 해주세요.";
    } else {
      msg = "비밀번호는 대소문자, 숫자, 특수문자 (!@#$%^&)를 포함해야해요.";
    }
  } else if (!koreanReg.test(e.target.name.value)) {
    if (e.target.name.value.length > 10) {
      msg = "이름의 길이는 10글자 이하로 해주세요.";
    } else if (e.target.name.value.length === 0) {
      msg = "이름을 입력하세요.";
    } else {
      msg = "한글만 입력하세요.";
    }
  } else if (!phoneReg.test(tempPhone)) {
    if (tempPhone.length > 13) {
      msg = "전화번호의 길이는 11~13으로 해주세요.";
    } else if (tempPhone.length === 0) {
      msg = "전화번호를 입력해주세요.";
    } else {
      msg = "잘못된 전화번호를 입력했습니다.";
    }
  }

  if (msg) {
    e.preventDefault();
    alert(msg);
  }
};
