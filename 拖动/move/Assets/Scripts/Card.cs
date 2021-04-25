using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Card : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    private bool isMouseDown = false;
    private Vector3 lastMousePosition = Vector3.zero;

	void Update () {
	    if (Input.GetMouseButtonDown(0))
	    {
	        isMouseDown = true;
	    }
	    if (Input.GetMouseButtonUp(0))
	    {
	        isMouseDown = false;
	        lastMousePosition = Vector3.zero;
	    }
	    if (isMouseDown)
	    {
	        if (lastMousePosition != Vector3.zero)
	        {
	            Vector3 offset = Camera.main.ScreenToWorldPoint(Input.mousePosition) - lastMousePosition;
	            this.transform.position += offset;
	        }
            lastMousePosition = Camera.main.ScreenToWorldPoint(Input.mousePosition);
	        
	    }
	}
}
