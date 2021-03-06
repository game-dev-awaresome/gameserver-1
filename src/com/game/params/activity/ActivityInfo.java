package com.game.params.activity;

import java.util.List;
import java.util.ArrayList;
import com.game.params.*;

//活动信息(工具自动生成，请勿手动修改！）
public class ActivityInfo implements IProtocol {
	public List<Integer> id;//活动列表
	public List<ActivityTaskVO> tasks;//任务列表


	public void decode(BufferBuilder bb) {
		this.id = bb.getIntList();
		
        if (bb.getNullFlag())
            this.tasks = null;
        else {
            int length = bb.getInt();
            this.tasks = new ArrayList<ActivityTaskVO>();
            for (int i = 0; i < length; i++)
            {
                //如果元素不够先创建一个，Java泛型创建对象，性能？
                boolean isNull = bb.getNullFlag();

                //如果不是null就解析
                if(isNull)
                {
                    this.tasks.add(null);
                }
                else
                {
                    ActivityTaskVO instance = new ActivityTaskVO();
                    instance.decode(bb);
                    this.tasks.add(instance);
                }

            }
        }
	}

	public void encode(BufferBuilder bb) {
		bb.putIntList(this.id);
		bb.putProtocolVoList(this.tasks);
	}
}
