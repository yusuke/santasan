/*
Copyright (c) 2012, Yusuke Yamamoto
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the Yusuke Yamamoto nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY Yusuke Yamamoto ``AS IS'' AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL Yusuke Yamamoto BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package santa;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

import java.io.IOException;

/**
 * @author Yusuke Yamamoto <yusuke at mac.com>
 */
public class Store {
    static Cache<String , Object> cache;
    static Cache<String , Object> tempCache;
    static{
        try {
            cache = new DefaultCacheManager(Store.class.getResourceAsStream("/infinispan.xml")).getCache("xml-configured-cache");
            tempCache = new DefaultCacheManager(Store.class.getResourceAsStream("/infinispan.xml")).getCache("temporary-cache");
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    public static void put(String str, Object obj){
        cache.put(str,obj);
    }
    public static Object get(String str){
        return cache.get(str);
    }

    public static void putTemporal(String key, Object obj){
        tempCache.put(key,obj);
    }
    public static Object getTemporal(String key){
        return tempCache.get(key);
    }
}
