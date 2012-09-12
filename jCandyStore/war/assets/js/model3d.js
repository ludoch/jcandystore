/*
  Copyright 2011 Google Inc.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.

  Original version: Martin Gorner (mgorner@google.com)
*/

// global animation control
var animation = null;


function modelshow(el, modelUrl)
{	
	// dimensions
	var canvascontainer = el;
	var width = canvascontainer.clientWidth;
	var height = canvascontainer.clientHeight; // unfathomable bug: why do I get a scroll bar with the full height ???
	
	// renderer
	var renderer = new THREE.WebGLRenderer ({antialias: true});
	renderer.setSize(width, height);
	
	// glue to HTML element
	canvascontainer.insertBefore(renderer.domElement,canvascontainer.firstChild);
	renderer.setClearColorHex(0x000000, 0);
	renderer.clear();
	
	// camera
	var camera = new THREE.PerspectiveCamera(35, width / height, 1, 3000);
	camera.position.x = 0;
	camera.position.y = 40;
	camera.position.z = 110;
	camera.rotation.x = -Math.PI/2/10;
	
	// scene
	var scene = new THREE.Scene();
	
	// lights
	var light1 = new THREE.DirectionalLight(0xffffff, 1);
	light1.position.set(1, 1, 0.3);
	scene.add(light1);
	
	var light2 = new THREE.DirectionalLight(0xffffff, 1);
	light2.position.set(-1, 1, -0.3);
	scene.add(light2);
	
	var light3 = new THREE.DirectionalLight(0xffffff, 0.4);
	light3.position.set(0, 0, 1);
	scene.add(light3);
	
	// launch animation
	animation = new Object();
	animation.renderer = renderer;
	animation.scene    = scene;
	animation.camera   = camera;
	animation.controls = null;
	animation.clock    = null;
	animation.gettingclose_event = null;
	window.requestAnimationFrame(animate, renderer.domElement);
	
	var vec0 = new THREE.Vector3(0,0,0);
	var vec111 = new THREE.Vector3(1,1,1);
	
	var sequencer = new Object();
	sequencer.todo = new Object();
	sequencer.begin = 0;
	sequencer.end = 0;
	sequencer.add = function(f) {sequencer.todo[sequencer.end++] = f;};
	sequencer.go = function() {if (sequencer.begin < sequencer.end) sequencer.todo[sequencer.begin++]();};
	
	loadModel(scene, modelUrl,      new THREE.Vector3(0,0,0),   vec0, vec111, "androidmodel-gingerbread", "progress-gingerbread", sequencer);
	
	window.setTimeout(function(){sequencer.go();}, 100);
}

function modelhide(el)
{
	var el     = document.querySelector('.current');
	//var body   = document.querySelector('body');
	var canvascontainer = document.getElementById('modelcontainer');
	
	el.classList.remove('modellaunch');
	//body.classList.remove('spacelaunch');
	
	el.onclick = function() {modelshow.apply(this);};
	
	// kill the 3D canvas too
	window.setTimeout( function() {
	if (canvascontainer !== null && canvascontainer.firstChild.tagName == 'CANVAS')
		canvascontainer.removeChild(canvascontainer.firstChild);
	if (animation !== null && animation.controls !== null)
		animation.controls.removeEventListeners();
	animation = null;
	}, 100);
}


function loadModel(scene, url, position, rotation, scale, name, progressBarId, sequencer)
{	
	// instantiate a loader
	var loader = new THREE.ColladaLoader();
	loader.options.convertUpAxis= true;
	loader.options.upAxis = 'Y';
	
	var loadheight = 50;
	
	// create a loding shape (kind of 3D progres bar)
	var loadingGeometry = new THREE.CylinderGeometry(20, 20, loadheight, 20, 10, false);
	var loadingMaterial = new THREE.MeshBasicMaterial({color: 0x8888ff, wireframe: true, wireframeLinewidth: 1});
	var loadingShape    = new THREE.Mesh(loadingGeometry, loadingMaterial);
	
	var loadedGeometry  = new THREE.CylinderGeometry(15, 15, loadheight, 20, 10, false);
	var loadedMaterial  = new THREE.MeshLambertMaterial({color: 0xffffff, emissive: 0x444444});
	var loadedShape     = new THREE.Mesh(loadedGeometry, loadedMaterial);
	
	loadingShape.name = "loading-" + name;
	loadingShape.translateX(position.x);
	loadingShape.translateY(position.y + loadheight/2);
	loadingShape.translateZ(position.z);
	loadingShape.rotation.copy(rotation);
	
	loadedShape.scale.set(1, 0.01, 1);
	loadedShape.name = "loaded-" + name;
	loadedShape.translateX(position.x);
	loadedShape.translateY(position.y + 0.01*loadheight/2);
	loadedShape.translateZ(position.z);
	loadedShape.rotation.copy(rotation);
	
	// make the loading shape appear
	scene.add(loadingShape);
	scene.add(loadedShape);
	
	// add the actual loading task into a sequential queue
	sequencer.add( function() {
		loader.load(url,
				function(collada)
				{
					var model = collada.scene;
					model.position.copy(position);
					model.rotation.copy(rotation);
					model.scale.copy(scale);
					model.name = name;
					setProgress3D(scene, model, loadingShape, loadedShape, loadheight, 100);
					
					window.setTimeout(function(){sequencer.go();}, 300);
				},
				function(progress)
				{
					var percent = 0;
					if (progress.lengthComputable)
						percent = progress.loaded / progress.total*100;
					else
						percent = progress.loaded / (1024*1024) * 100;					
					setProgress3D(scene, null, loadingShape, loadedShape, loadheight, percent);
				}
		);}
	);
}

function setProgress3D(scene, model, waitShape, progressShape, progressShapeMaxHeight, percent)
{
	var f = percent/100;
	if (f>1)
		f = 1;
	var t0 = new Date().getTime();
	var f0 = progressShape.scale.y;
	if (f0 == f)
		return;
	progressShape.continuousUpdate = function(t)
	{
		var duration = 300;
		var intermediateScale = f;
		if (t-t0 <= duration)
			intermediateScale = (t-t0)/duration*f + (1-(t-t0)/duration)*f0;
		
		progressShape.translateY((intermediateScale-progressShape.scale.y)*progressShapeMaxHeight/2);
		progressShape.scale.set(1, intermediateScale, 1);
		
		// if animation time exhausted
		// undefine the function and if the loading is finished, also swap in the real model
		if (t-t0 > duration)
		{
			if (f == 1)
			{
				scene.remove(waitShape);
				scene.remove(progressShape);
				if (model !== null)
					scene.add(model);
			}
			delete this.continuousUpdate;
		}
	};
}

function animate(t)
{
	
	// if no animation, return and don't relaunch animation loop
	if (animation == null)
		return;
	
	var renderer = animation.renderer;
	var scene    = animation.scene;
	var camera   = animation.camera;
	var controls = animation.controls;
	var clock    = animation.clock;
	
	var objectsToUpdate = new Object();
	var nbObjectsToUpdate = 0;
	
	THREE.SceneUtils.traverseHierarchy(scene, function(object)
	{
		// models: slow rotation
		if (object instanceof THREE.Object3D && object.name.indexOf("androidmodel") >= 0)
		{
			object.rotation.y = t/1000;
		}
		
		// loading shapes: fast rotation
		if (object instanceof THREE.Object3D && object.name.indexOf("loading") >= 0)
		{
			object.rotation.y = t/500;
		}
		
		// loading progress shapes: continuously update height
		if (object instanceof THREE.Object3D && object.name.indexOf("loaded") >= 0)
		{
			if (object.continuousUpdate !== undefined)
				objectsToUpdate[nbObjectsToUpdate++] = object;
		}
	});
	
	// the progress shape animation also switches in the final loading shape
	// when it finishes so these calls must be made outside of the scene traversal loop
	for (var i=nbObjectsToUpdate-1; i>=0; i--)
	{
		objectsToUpdate[i].continuousUpdate(t);
		delete objectsToUpdate[i];
	}
	
	if (clock !== null && controls !== null)
	{
		var delta = clock.getDelta();
		controls.update(delta);
	}
	renderer.render(scene, camera);
	
	// launch an event if the camera gets closer than 900px
	if (animation.gettingclose_event !== null && !animation.gettingclose_event_fired && camera.position.lengthSq() < 900*900)
	{
		animation.gettingclose_event();
		animation.gettingclose_event_fired = true;
	}
	
	// keep going
	window.requestAnimationFrame(animate, renderer.domElement);
}
