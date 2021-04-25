using System.Collections;
using System.Collections.Generic;
using UnityEngine.UI;
using UnityEngine;

public class NewBehaviourScript : MonoBehaviour
{
    void Awake()
    {
        
    }

    // Start is called before the first frame update
    void Start()
    {
        
        GetComponent<Slider>().onValueChanged.AddListener(delegate(float value){
		Debug.LogFormat("value = {0}", value);
		});
    }

    // Update is called once per frame
    void Update()
    {

    }
    
}