using System.Collections;
using System.Collections.Generic;
using UnityEngine.UI;
using UnityEngine;

public class NewBehaviourScript : MonoBehaviour
{
    public GameObject slider;

    
    void Awake()
    {
        
    }

    // Start is called before the first frame update
    void Start()
    {
        slider = GameObject.Find("Canvas/Slider");
        slider.GetComponent<Slider>().onValueChanged.AddListener(delegate(float value){
		Debug.LogFormat("value = {0}", value);
		});
    }

    // Update is called once per frame
    void Update()
    {

    }
    
}