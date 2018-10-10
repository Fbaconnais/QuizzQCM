function createXHR() {
			if (window.XMLHttpRequest) {
				xhr = new XMLHttpRequest();
			} else if (window.ActiveXObject) //  Internet Explorer
			{
				xhr = new ActiveXObject("Msxml2.XMLHTTP");
			}

			return xhr;

		}