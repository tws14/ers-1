$(function(){
	
  $.validator.addMethod(
	"onlyNum",
	function(value, element){
		return this.optional(element) || /^([0-9]+)$/.test(value);
	},
	"ハイフンなし半角数字のみ"
);

 $.validator.addMethod(
	"NotSpace",
	function(value, element) {
		return this.optional(element) || value.trim().length > 0;
	},
	"空白は許可されていません"
);
	
  $('#register-form').validate({
	rules: {
		lastName:  {
			required: true,
			NotSpace: true,
			maxlength: 20,
		},
		firstName: {
			required: true,
			NotSpace: true,
			maxlength: 20,
		},
		gender: {
			required: true,
		},
		dept: {
			required: true
		},
		birth: {
			required: true,
		},
		email: {
			required: true,
			email: true,
			maxlength: 60,
			NotSpace: true,
		},
		tel: {
			required: true,
			onlyNum: true,
			minlength: 10,
			maxlength: 11,
			NotSpace: true,
		},
		zipcode: {
			required: true,
			onlyNum: true,
			minlength: 7,
			maxlength: 7,
			NotSpace: true,
		},
		address1: {
			required: true,
			NotSpace: true,
			maxlength: 30,
		},
		address2: {
			required: true,
			NotSpace: true,
			maxlength: 30,
		},
		address3: {
			maxlength: 30,
		},
		school1: {
			required: true,
			NotSpace: true,
			maxlength: 30,
		},
		school2: {
			maxlength: 30,
		},
		skill: {
			required: true,
			NotSpace: true,
			maxlength:30,
		},
	},
	
	messages: {
		lastName: {
			required: "必須項目です",
			maxlength: "20字以内",
		},
		firstName: {
			required: "必須項目です",
			maxlength: "20字以内",
		},
		gender: {
			required: "性別を選択してください",
		},
		dept: {
			required: "部署を選択してください",
		},
		birth: {
			required: "必須項目です",
		},
		email: {
			required: "必須項目です",
			email: "メールアドレス形式で入力してください",
			maxlength: "60字以内",
		},
		tel: {
			required: "必須項目です",
			minlength: "10~11桁です",
			maxlength: "10~11桁です",
		},
		zipcode: {
			required: "必須項目です",
			minlength: "7桁です",
			maxlength: "7桁です",
		},
		address1: {
			required: "必須項目です",
			maxlength: "30字以内",
		},
		address2: {
			required: "必須項目です",
			maxlength: "30字以内",
		},
		address3: {
			maxlength: "30字以内",
		},
		school1: {
			required: "必須項目です",
			maxlength: "30字以内",
		},
		school2: {
			maxlength: "30字以内",
		},
		skill: {
			required: "必須項目です",
			maxlength: "30字以内",
		},
	},

	errorClass: "validation-error",
	errorElement: "span",
	
	errorPlacement: function(error, element) {
		error.appendTo(element.data("error_place"));
	},
	
	});
});