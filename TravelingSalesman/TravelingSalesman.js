//global variable that stores all x,y pairs
var listPoints = [];

//draws specified number of points to each canvas at same loc. adds them to global list
function initPoints()
{
	var num = document.getElementById("numDots").value;
	var ctx1 = canvas.getContext("2d");
	var ctx2 = canvas2.getContext("2d");
	var ctx3 = canvas3.getContext("2d");
	for(var index = 0; index < num; index++)
	{
		var x = Math.random() * 301;
		var y = Math.random() * 151;
		listPoints.push([x,y]);
		ctx1.fillRect(x,y,1,1);
		ctx2.fillRect(x,y,1,1);
		ctx3.fillRect(x,y,1,1);
	}
}

//gets the distance between two points using distance formula
function getDist(ptOne, ptTwo)
{
	var x = ptOne[0];
	var y = ptOne[1];
	var xx = ptTwo[0];
	var yy = ptTwo[1];
	var xD = Math.pow((x - xx),2);
	var yD = Math.pow((y-yy),2);
	var dist = Math.sqrt(xD + yD);
	return dist;
}

//gets total distance of a list of points by repeatedly calling getDist
function getTotDist(inOrder)
{
	var dist = 0;
	for(var i = 0; i < inOrder.length - 1; i++)
	{
		dist += getDist(inOrder[i],inOrder[i+1]);
	}
	return dist;
}

//draws a line between two points on canvas 3 for greedy algorithm
function drawGreedyLine(x,y,xx,yy)
{
	var ctx3 = canvas3.getContext("2d");
	ctx3.beginPath()
	ctx3.moveTo(x,y);
	ctx3.lineTo(xx,yy);
	ctx3.stroke();
}

//draws a line between two points on canvas 2 for nearest neighbor algorithm
function drawNearestLine(x,y,xx,yy)
{
	var ctx2 = canvas2.getContext("2d");
	ctx2.beginPath()
	ctx2.moveTo(x,y);
	ctx2.lineTo(xx,yy);
	ctx2.stroke();
}

//draws a line between two points on canvas 1 for smallestIncrease algorithm
function drawShortestLine(x,y,xx,yy)
{
	var ctx1 = canvas.getContext("2d");
	ctx1.beginPath()
	ctx1.moveTo(x,y);
	ctx1.lineTo(xx,yy);
	ctx1.stroke();
}

//greedy algorithm that draws to the next closest point of current point
function greedy()
{
	var copyPoints = listPoints.slice();
	var shortestDist = 100000;
	var currPoint = copyPoints[0];
	copyPoints.splice(0,1);
	while(copyPoints.length > 0)
	{
		var indexNear = -1;
		for(var index = 0; index < copyPoints.length; index++)
		{	
			if(getDist(currPoint, copyPoints[index]) < shortestDist)
			{
				shortestDist = getDist(currPoint, copyPoints[index]);
				indexNear = index;
			}
		}
		drawGreedyLine(currPoint[0],currPoint[1],copyPoints[indexNear][0],copyPoints[indexNear][1]);
		currPoint = copyPoints[indexNear];
		copyPoints.splice(indexNear,1);
		shortestDist = 100000;
	}
}

//smallestIncrease algorithm that looks at the change in distances and chooses the one of smallest increase
function smallestIncrease()
{
	var copyPoints = listPoints.slice();
	var inOrder = [];
	inOrder[0] = copyPoints[0];
	inOrder[1] = copyPoints[1];
	copyPoints.splice(0,1);
	copyPoints.splice(0,1);
	var smallestIncrease = Number.MAX_VALUE;
	while(copyPoints.length > 0)
	{
		var totalDist = getTotDist(inOrder);
		var indexShortest = -1;
		for(var index = 0; index < inOrder.length + 1; index++)
		{
			if (index == inOrder.length)
				inOrder.push(copyPoints[0])
			else
				inOrder.splice(index,0,copyPoints[0]);
			var newDist = getTotDist(inOrder);
			var increase = newDist - totalDist;
			if(increase < smallestIncrease)
			{
				indexShortest = index;
				smallestIncrease = increase;
			}
			inOrder.splice(index,1);
		}
		smallestIncrease = Number.MAX_VALUE;
		console.log(indexShortest);
		inOrder.splice(indexShortest,0,copyPoints[0]);
		copyPoints.splice(0,1);
	}
	for(var i = 0; i < inOrder.length - 1; i++)
	{
		drawShortestLine(inOrder[i][0],inOrder[i][1],inOrder[i+1][0],inOrder[i+1][1])
	}
}

//nearest neighbor algorithm that puts a point next to whichever one it is closest to, looking through the entire array first
function nearestNeighbor()
{
	var copyPoints = listPoints.slice();
	var inOrder = [];
	inOrder[0] = copyPoints[0];
	inOrder[1] = copyPoints[1];
	copyPoints.splice(0,1);
	copyPoints.splice(0,1);
	while(copyPoints.length > 0)
	{
		var nearestDist = 100000;
		var indexNear = -1;
		for(var index = 0; index < inOrder.length; index++)
		{
			if(getDist(copyPoints[0],inOrder[index]) < nearestDist)
			{
				nearestDist = getDist(copyPoints[0],inOrder[index]);
				indexNear = index;
			}
		}
		inOrder.splice(indexNear + 1,0,copyPoints[0]);
		copyPoints.splice(0,1);
	}
	for(var i = 0; i < inOrder.length - 1; i++)
	{
		drawNearestLine(inOrder[i][0],inOrder[i][1],inOrder[i + 1][0],inOrder[i + 1][1]);
	}
}