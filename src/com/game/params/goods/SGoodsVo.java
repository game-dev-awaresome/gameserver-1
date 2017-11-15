package com.game.params.goods;

import java.util.List;
import java.util.ArrayList;
import com.game.params.*;

//玩家物品(工具自动生成，请勿手动修改！）
public class SGoodsVo implements IProtocol {
	public long id;//唯一id
	public int goodsId;//物品id
	public int stackNum;//叠加数量
	public byte storeType;//存储类型 0背包1角色穿戴2阵位装备
	public List<AttrItem> addAttrs;//附加属性
	public List<AttrItem> lastAttrs;//上次洗练出来的属性
	public byte star;//星
	public boolean bLock;//锁定标记


	public void decode(BufferBuilder bb) {
		this.id = bb.getLong();
		this.goodsId = bb.getInt();
		this.stackNum = bb.getInt();
		this.storeType = bb.getByte();
		
        if (bb.getNullFlag())
            this.addAttrs = null;
        else {
            int length = bb.getInt();
            this.addAttrs = new ArrayList<AttrItem>();
            for (int i = 0; i < length; i++)
            {
                //如果元素不够先创建一个，Java泛型创建对象，性能？
                boolean isNull = bb.getNullFlag();

                //如果不是null就解析
                if(isNull)
                {
                    this.addAttrs.add(null);
                }
                else
                {
                    AttrItem instance = new AttrItem();
                    instance.decode(bb);
                    this.addAttrs.add(instance);
                }

            }
        }
		
        if (bb.getNullFlag())
            this.lastAttrs = null;
        else {
            int length = bb.getInt();
            this.lastAttrs = new ArrayList<AttrItem>();
            for (int i = 0; i < length; i++)
            {
                //如果元素不够先创建一个，Java泛型创建对象，性能？
                boolean isNull = bb.getNullFlag();

                //如果不是null就解析
                if(isNull)
                {
                    this.lastAttrs.add(null);
                }
                else
                {
                    AttrItem instance = new AttrItem();
                    instance.decode(bb);
                    this.lastAttrs.add(instance);
                }

            }
        }
		this.star = bb.getByte();
		this.bLock = bb.getBoolean();
	}

	public void encode(BufferBuilder bb) {
		bb.putLong(this.id);
		bb.putInt(this.goodsId);
		bb.putInt(this.stackNum);
		bb.putByte(this.storeType);
		bb.putProtocolVoList(this.addAttrs);
		bb.putProtocolVoList(this.lastAttrs);
		bb.putByte(this.star);
		bb.putBoolean(this.bLock);
	}
}
