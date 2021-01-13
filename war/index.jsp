<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
</head>
<body>
  <canvas id="canvas"></canvas>
    <div id='title'>
    	<p class="word">UNBOX<br>THE<br>UNKNOWNS</p>
    </div>
	<div id='searchBar'>
		<form action='${requestUri}' name="getkey" method='get'>
			<div id='searchBarStyle'>
        <input id='keyWordInput' type='text' name='keyword' placeholder = 'keyword'/>
        <div id='buttongroup'>  
          <input class="button" id='youtubeButton'  type='submit' name="button" value='YT' />
          <input class="button" id='youtubeButton'  type='submit' name="button" value='YT_c' />
          <input class="button" id='searchButton'  type='submit' name="button" value='YT_in' />
          <input class="button" id='searchButton'  type='submit' name="button" value='G' />
          <input class="button" id='searchButton'  type='submit' name="button" value='G_c' />
          <input class="button" id='searchButton'  type='submit' name="button" value='G_in' />
        </div>
			</div>	
		</form>
	</div>
</body>

<style>
body,html {
  background: #060e1b;
	overflow: hidden;
  height: 100%;
  width: 100%;
  margin: 0;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
canvas {
		  opacity: 0.5;
		  position:absolute;
		  top:0;
		  left:0;
		}
canvas2{
  position:absolute;
  opacity: 0.5;
  z-index=-1
}
#title{
  z-index:1;
  padding: 0;
/*   border: 2px solid white; */
  margin-top: 0px;
}
.word{
  display: inline;
  z-index:1;
  color: white;
  font-size: 3.1em;
  font-weight: bold;
  font-family: "Lucida Console", "Courier New", monospace;
  line-height: 1.5em;
  letter-spacing: 0.3em;
}
.button{
	positive: relative;
  opacity: 0.7;
	outline: none;
	width: 15%;
	height: 3em;
  border: 2px solid white; 
	border-radius: 4px;
  transition: all .2s ease;
}
.button:hover{
  opacity: 1;
  box-shadow: inset 0 0 0 3px #fa0;
}
#keyWordInput{
  border: 2px solid yellow;
  background-color: ;
  border-radius: 4px;
  outline: none;
/* 	positive: relative; */
	width: 80%;
	height: 2.5em;
	padding: 0.3em;
  margin: 1em;
/*   margin-right: 2em; */
	font-size: 20px;
}
#searchBarStyle{
  justify-content: center;
  align-item: center;
  text-align: center;
	width: 100%;
	padding: 0.3em;
/* 	border: 1px solid black; */
/* 	border-radius: 4px; */
}
input {
	border: none;
}
form {
	width: 70%;
	display: flex;
	justify-content: center;
}
#searchBar{
  z-index:1;
	width: 100%;
	display: flex;
	justify-content: center;
  align-items: center;
}
#buttongroup{
  display: inline-block;
  width: 100%;
/*   border: 1px solid black; */
}
</style>
</html>
<script>
function fn() {
    window.requestAnimFrame = (function () {
        return window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || function (callback) {
                    window.setTimeout(callback, 1000 / 60);
                }
    })();
    var canvas = document.getElementById('canvas'), ctx = canvas.getContext('2d'), w = canvas.width = window.innerWidth, h = canvas.height = window.innerHeight, hue = 217, stars = [], count = 0, maxStars = 1200;
    var canvas2 = document.createElement('canvas'), ctx2 = canvas2.getContext('2d');
    canvas2.width = 100;
    canvas2.height = 100;
    var half = canvas2.width / 2, gradient2 = ctx2.createRadialGradient(half, half, 0, half, half, half);
    gradient2.addColorStop(0.025, '#fff');
    gradient2.addColorStop(0.1, 'hsl(' + hue + ', 61%, 33%)');
    gradient2.addColorStop(0.25, 'hsl(' + hue + ', 64%, 6%)');
    gradient2.addColorStop(1, 'transparent');
    ctx2.fillStyle = gradient2;
    ctx2.beginPath();
    ctx2.arc(half, half, half, 0, Math.PI * 2);
    ctx2.fill();
    // ctx2.opacity
    function random(min, max) {
        if (arguments.length < 2) {
            max = min;
            min = 0
        }
        if (min > max) {
            var hold = max;
            max = min;
            min = hold
        }
        return Math.floor(Math.random() * (max - min + 1)) + min
    }

    function maxOrbit(x, y) {
        var max = Math.max(x, y), diameter = Math.round(Math.sqrt(max * max + max * max));
        return diameter / 2
    }

    var Star = function () {
        this.orbitRadius = random(maxOrbit(w, h));
        this.radius = random(60, this.orbitRadius) / 12;
        this.orbitX = w / 2;
        this.orbitY = h / 2;
        this.timePassed = random(0, maxStars);
        this.speed = random(this.orbitRadius) / 900000;
        this.alpha = random(2, 10) / 10;
        count++;
        stars[count] = this
    };
    Star.prototype.draw = function () {
        var x = Math.sin(this.timePassed) * this.orbitRadius + this.orbitX, y = Math.cos(this.timePassed) * this.orbitRadius + this.orbitY, twinkle = random(10);
        if (twinkle === 1 && this.alpha > 0) {
            this.alpha -= 0.05
        } else if (twinkle === 2 && this.alpha < 1) {
            this.alpha += 0.05
        }
        ctx.globalAlpha = this.alpha;
        ctx.drawImage(canvas2, x - this.radius / 2, y - this.radius / 2, this.radius, this.radius);
        this.timePassed += this.speed
    };
    for (var i = 0; i < maxStars; i++) {
        new Star()
    }
    function animation() {
        ctx.globalCompositeOperation = 'source-over';
        ctx.globalAlpha = 0.8;
        ctx.fillStyle = 'hsla(' + hue + ', 64%, 6%, 1)';
        ctx.fillRect(0, 0, w, h);
        ctx.globalCompositeOperation = 'lighter';
        for (var i = 1, l = stars.length; i < l; i++) {
            stars[i].draw()
        }
        ;
        window.requestAnimationFrame(animation)
    }

    animation();
}
fn();
</script>