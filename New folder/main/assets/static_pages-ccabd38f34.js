$(document).ready(function(){
	var canvasDiv = document.getElementById('particle-canvas');
	var options = {
		particleColor: '#2a4460',
		interactive: true,
		speed: 'high',
		density: 'high'
	};
	//https://raw.githubusercontent.com/JulianLaval/canvas-particle-network
	var particleCanvas = new ParticleNetwork(canvasDiv, options);
	$( "body" ).mousemove(function( event ) {
		$("#particle-canvas").css('transform', 'translate(' + event.pageY /20 + 'px,' + -event.pageX /20+ 'px)');
	});
	$('.alert-box-notify').animate({
		bottom: "8vh",
		opacity: 1
	},function(){
		$(this).fadeOut(8000);
	});
	$('.alert-box-notify-1').animate({
		bottom: "16vh",
		opacity: 1
	},function(){
		$(this).fadeOut(8000);
	});
	$('#live-events-placeholder').click(function(){
		if($('#live-events-loading').height()>150){
			$("#live-events-loading").animate({
				height: '0px'
			},"slow",function(){
				$(this).animate({
					width:'0px'
				},"slow",function(){
					$(this).css({border:'0px solid #1175e5'});
				});
			});
		}else{
			$("#live-events-loading").css({border:'1px solid #1175e5'}).animate({
				width:'200px'
			},function(){
				$(this).animate({
					height:'160px'
				});
			});

		}
	});
	$("body").click(function(){
		if($('#live-events-loading').height()>150){
			$("#live-events-loading").animate({
				height: '0px'
			},function(){
				$(this).animate({
					width:'0px'
				},function(){
					$(this).css({border:'0px solid #1175e5'});
				});
			});
		}
	});
});