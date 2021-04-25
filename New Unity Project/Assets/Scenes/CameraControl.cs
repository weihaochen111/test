using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraControl : MonoBehaviour
{
    

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
        if (Input.GetAxis("Mouse ScrollWheel") <0)  
        {  
           if(Camera.main.fieldOfView<=100)  
           Camera.main.fieldOfView +=2;  
           if(Camera.main.orthographicSize<=20)  
           Camera.main.orthographicSize +=0.5F;  
        }  
        //Zoom in  
        if (Input.GetAxis("Mouse ScrollWheel") > 0)  
        {  
          if(Camera.main.fieldOfView>2)  
            Camera.main.fieldOfView-=2;  
          if(Camera.main.orthographicSize>=1)  
            Camera.main.orthographicSize-=0.5F;  
        }
    }
}
